package com.reverb.recipechallenge.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.reverb.recipechallenge.databinding.SavedFragmentLayoutBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealEntity
import com.reverb.recipechallenge.util.Resource
import com.reverb.recipechallenge.view.adapter.FavoritesAdapter
import com.reverb.recipechallenge.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment: Fragment() {
    private lateinit var binding: SavedFragmentLayoutBinding

    private val favoritesViewModel by viewModels<FavoritesViewModel>()

    private val favoritesAdapter by lazy{
        FavoritesAdapter(
            onItemSelected = {  mealEntity -> onItemSavedSelected(mealEntity) },
            onItemDeleted = { mealId, position -> onDeleteItem(mealId, position) }
        )
    }

    private var searchSavedTextWatcher = object: TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            favoritesAdapter.differList.currentList.filter {mealEntity ->
                mealEntity.mealName.contains(p0.toString())
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SavedFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            getAllFavoriteMealsFromDb()
        }

        initSavedRv()

        binding.tietLookSavedRecipe.addTextChangedListener(searchSavedTextWatcher)

        //get all saved meals
        lifecycleScope.launchWhenStarted {
            favoritesViewModel.allFavoriteMeals.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        favoritesAdapter.differList.submitList(it.data)
                    }
                    is Resource.Error -> {}
                    else -> Unit
                }
            }
        }

        //collect the state of deleted meal
        lifecycleScope.launchWhenStarted {
            favoritesViewModel.mealEvents.collect(){
                if(it is FavoritesViewModel.FavoriteMealsEvents.ShowUndoSnackBar){
                    Snackbar.make(requireView(), it.msg, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }//ON VIEW CREATED


    private fun onItemSavedSelected(mealEntity: MealEntity){
        val actions = SavedFragmentDirections.actionSaveFragmentToRecipeFragment(mealEntity.mealId)
        findNavController().navigate(actions)
    }

    private fun onDeleteItem(mealId:String, itemPosition: Int){
        //DELETE FROM THE RV THAT ITEM
        val updatedList: MutableList<MealEntity> = favoritesAdapter.differList.currentList.toMutableList()
        updatedList.removeAt(itemPosition)

        favoritesAdapter.differList.submitList(updatedList)
        favoritesViewModel.deleteFavoriteMeal(mealId)
    }

    private fun initSavedRv() {
        binding.rvSavedMeals.apply {
            adapter = favoritesAdapter
            layoutManager = GridLayoutManager( requireContext(), 2, GridLayoutManager.VERTICAL, false )
        }
    }

    suspend fun getAllFavoriteMealsFromDb(){
        coroutineScope {
            val deferred = async {  favoritesViewModel.favoriteMeals()  }
            deferred.await()
        }
    }

}