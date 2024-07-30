package com.reverb.recipechallenge.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.reverb.recipechallenge.R
import com.reverb.recipechallenge.databinding.RecipeFragmentLayoutBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.toMealEntity
import com.reverb.recipechallenge.util.Resource
import com.reverb.recipechallenge.util.mergeMealIngredientsMeasure
import com.reverb.recipechallenge.viewmodel.FavoritesViewModel
import com.reverb.recipechallenge.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RecipeFragment: Fragment() {

    private lateinit var binding: RecipeFragmentLayoutBinding

    private val mealViewModel by viewModels<MealViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()

    private val args by navArgs<RecipeFragmentArgs>()
    private lateinit var mealGotten: Meal


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipeFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealId = args.mealIdToSearch

        //mealViewModel.getMealById(mealId)
        lifecycleScope.launch{
            searchMealById(mealId)
        }




        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mealViewModel.mealsById.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        println("Meal Object By id: ${it.data?.strMeal}  ${it.data?.strArea}")
                        mealGotten = it.data!!
                        showMealInUi(it.data)
                    }
                    is Resource.Error -> { Log.e("RecipeFragmentError", it.toString()) }
                    else -> Unit
                }
            }
        }


        //save meal into favorites
        binding.mtvFoodRecipeSaveRecipe.setOnClickListener {
            saveMealToDb(mealGotten)
        }

        /**
         *  From [RecipeFragment] I will navigate to [SavedFragment] aka FavoritesFragment
         *  after the user inserts a meal to favorites database
         **/
        lifecycleScope.launchWhenStarted {
            favoritesViewModel.mealEvents.collect(){
                if(it is FavoritesViewModel.FavoriteMealsEvents.NavigateToFavoritesMeals){
                    findNavController().navigate(R.id.action_recipeFragment_to_saveFragment)
                }
            }
        }
    }

    private fun saveMealToDb(meal: Meal){
        favoritesViewModel.insertfavoriteMeal(meal.toMealEntity())
    }

    private fun showMealInUi(meal: Meal){
        binding.apply {
            Glide.with(ivFoodRecipeImage.context).load(meal.strMealThumb).into(ivFoodRecipeImage)
            tvFoodRecipeTitle.text = meal.strMeal
            mtvFoodRecipeTime.text = meal.idMeal.substring(0,2) + " min"
            tvIngredientsList.text = mergeMealIngredientsMeasure(meal)
            tvProcedureList.text = meal.strInstructions
        }
    }

    suspend fun searchMealById(mealId: String){
        coroutineScope {
            val deferred1 = async{ mealViewModel.getMealById(mealId) }
            deferred1.await()
        }

    }

}