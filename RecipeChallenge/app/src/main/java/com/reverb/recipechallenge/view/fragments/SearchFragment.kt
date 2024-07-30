package com.reverb.recipechallenge.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.reverb.recipechallenge.R
import com.reverb.recipechallenge.databinding.SearchFragmentLayoutBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealResume
import com.reverb.recipechallenge.model.datamodel.toMealEntity
import com.reverb.recipechallenge.util.Resource
import com.reverb.recipechallenge.view.adapter.CategoriesAdapter
import com.reverb.recipechallenge.view.adapter.SearchMealsAdapter
import com.reverb.recipechallenge.viewmodel.CategoryViewModel
import com.reverb.recipechallenge.viewmodel.FavoritesViewModel
import com.reverb.recipechallenge.viewmodel.MealViewModel
import com.reverb.recipechallenge.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint
class SearchFragment: Fragment() {
    private lateinit var binding: SearchFragmentLayoutBinding


    private val searchViewModel by viewModels<SearchViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()

    private val searchMealsAdapter by lazy {
        SearchMealsAdapter(
            onItemSelected = {mealItem -> onItemMealSelected(mealItem) },
            onSaveMeal = { mealItem -> onSaveSearchedMeal(mealItem) }
        )
    }
    private val searchCategoriesAdapter by lazy { CategoriesAdapter(){ position -> onCategorySelected(position) } }

    private var searchTextWatcher = object: TextWatcher{
        private var timer = Timer()
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.i("user searches", "before text changed")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.i("user searches", p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
            timer.cancel()
            timer = Timer()
            timer.schedule(object: TimerTask(){
                override fun run() {
                    //Log.i("user searches", "after text changed")
                    searchViewModel.searchMealsByName(p0.toString())
                }

            }, 500L)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchedMealsRv()
        initSearchByCategoriesRv()

        lifecycleScope.launch {
            getAllCategories()
        }

//        binding.tietLookForRecipe.addTextChangedListener { Log.i("user searches", it.toString())  }
        binding.tietLookForRecipe.addTextChangedListener(searchTextWatcher)



        //SEARCH MEALS BY NAME
        lifecycleScope.launch {
            searchViewModel.searchMealResponse.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        searchMealsAdapter.differList.submitList(it.data)
                    }
                    is Resource.Error -> { println(it) }
                    else -> Unit
                }
            }
        }


        //LIST OF CATEGORIES
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.categoriesResponse.collectLatest {
                when(it){
                    is Resource.Loading -> { }
                    is Resource.Success -> {
                        searchCategoriesAdapter.differList.submitList(it.data)
                    }
                    is Resource.Error -> { println("Errors occurred when getting categories") }
                    else -> Unit
                }
            }
        }


        //GET MEALS IDS AND SOME INFO BY CATEGORY AS A FILTER
        lifecycleScope.launchWhenStarted {
            searchViewModel.filteredByCategoryResponse.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        searchMealsAdapter.differList.submitList(it.data)
                    }
                    is Resource.Error -> { println(it) }
                    else -> Unit
                }
            }
        }

        /**
         *  From [SearchFragment] I will navigate to [SavedFragment] aka FavoritesFragment
         *  after the user inserts a meal to favorites database
         **/
        lifecycleScope.launchWhenStarted {
            favoritesViewModel.mealEvents.collect(){
                if( it is FavoritesViewModel.FavoriteMealsEvents.NavigateToFavoritesMeals ){
                    findNavController().navigate(R.id.action_searchFragment_to_saveFragment)
                }
            }
        }

    }


    suspend fun getAllCategories(){
        coroutineScope {
            val deferred1 = async {    searchViewModel.getListOfCategories()     }
            deferred1.await()
        }
    }

    private fun onSaveSearchedMeal(mealItem: Any){
        if (mealItem is Meal){
            favoritesViewModel.insertfavoriteMeal( mealItem.toMealEntity() )
        }

        if(mealItem is MealResume){
            favoritesViewModel.insertfavoriteMeal( mealItem.toMealEntity() )
        }
    }


    private fun onItemMealSelected(mealItem: Any){
        if (mealItem is MealResume){
            val actions = SearchFragmentDirections.actionSearchFragmentToRecipeFragment(mealItem.idMeal)
            findNavController().navigate(actions)
        }

        if (mealItem is Meal){
            val actions = SearchFragmentDirections.actionSearchFragmentToRecipeFragment(mealItem.idMeal)
            findNavController().navigate(actions)
        }
    }

    private fun onCategorySelected(position: Int){
        searchViewModel.getMealsIdByCategory(searchCategoriesAdapter.differList.currentList[position].strCategory)

        searchCategoriesAdapter.differList.currentList[position].isSelected = !searchCategoriesAdapter.differList.currentList[position].isSelected
        searchCategoriesAdapter.notifyItemChanged(position)
    }

    private fun initSearchByCategoriesRv() {
        binding.rvSearchedCategories.apply {
            adapter = searchCategoriesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }
    }

    private fun initSearchedMealsRv() {
        binding.rvSearchedMeals.apply {
            adapter = searchMealsAdapter
            layoutManager = GridLayoutManager(  requireContext(), 2, GridLayoutManager.VERTICAL, false   )
        }
    }

}//SEARCH FRAGMENT