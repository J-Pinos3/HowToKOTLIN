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

    private var mealFromDB: String? = null


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
                        println("Since Recipe Fragment Meal Object By id: ${it.data?.strMeal}  ${it.data?.strArea}")
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
            /*
            lifecycleScope.launch {
                getSavedMealByIdFromDB(mealGotten.idMeal)
            }

            if( mealFromDB != mealGotten.idMeal ){
                println( "Since Recipe ${mealFromDB} <--->  ${mealGotten.idMeal}" )
                saveMealToDb(mealGotten)
                mealFromDB = mealGotten.idMeal
            }else{
                Log.i("MEALVRECIPE IN DB", "That meal already exists in db")
            }
            */
             lifecycleScope.launch {
                 favoritesViewModel.favoriteMealByiD(mealGotten.idMeal)
                 favoritesViewModel.mealById.collectLatest{
                     when(it){
                         is Resource.Loading -> {}
                         is Resource.Success -> {
                             mealFromDB = it.data?.mealId
                             if(mealFromDB != mealGotten.idMeal){
                                 println("Since Recipe ${mealFromDB} <--->  ${mealGotten.idMeal}")
                                 saveMealToDb(mealGotten)
                                 mealFromDB = mealGotten.idMeal
                             }else{
                                 Log.i("MEALVRECIPE IN DB", "That meal already exists in db")
                             }
                         }
                         is Resource.Error -> {
                             Log.e("RecipeFragment", "Error fetching meal from DB: ${it.message}")
                             //there is no that meal by id in Db, so insert it
                             saveMealToDb(mealGotten)
                             mealFromDB = mealGotten.idMeal
                         }
                         else -> Unit
                     }
                 }
             }

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

        /*
        lifecycleScope.launchWhenStarted {
            favoritesViewModel.mealById.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        //that meal is already in db
                        println( "Since Recipe Got meal from DB? IN RECIPE  ${it.data?.mealName }**${it.data?.mealId }" )
                        mealFromDB = it.data?.mealId
                    }
                    is Resource.Error -> {
                        println("Saved meal by id: " +it.message.toString())
                    }
                    else -> Unit
                }
            }
        }

         */
    }

    private fun saveMealToDb(meal: Meal){
        favoritesViewModel.insertfavoriteMeal(meal.toMealEntity())
    }


    suspend fun getSavedMealByIdFromDB(idMeal: String){
        coroutineScope {
            val deferred3 = async { favoritesViewModel.favoriteMealByiD(idMeal) }
            deferred3.await()
        }
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