package com.reverb.recipechallenge.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.reverb.recipechallenge.databinding.RecipeFragmentLayoutBinding
import com.reverb.recipechallenge.util.Resource
import com.reverb.recipechallenge.viewmodel.MealViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeFragment: Fragment() {

    private lateinit var binding: RecipeFragmentLayoutBinding

    private val mealViewModel by viewModels<MealViewModel>()

    private val args by navArgs<RecipeFragmentArgs>()


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
                    }
                    is Resource.Error -> { Log.e("RecipeFragmentError", it.toString()) }
                    else -> Unit
                }
            }
        }
    }

    suspend fun searchMealById(mealId: String){
        coroutineScope {
            val deferred1 = async{ mealViewModel.getMealById(mealId) }
            deferred1.await()
        }

    }

}