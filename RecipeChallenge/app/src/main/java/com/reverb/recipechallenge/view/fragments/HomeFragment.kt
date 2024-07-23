package com.reverb.recipechallenge.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reverb.recipechallenge.R
import com.reverb.recipechallenge.databinding.HomeFragmentLayoutBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealList
import com.reverb.recipechallenge.model.datamodel.MealResume
import com.reverb.recipechallenge.util.Resource
import com.reverb.recipechallenge.view.adapter.CategoriesAdapter
import com.reverb.recipechallenge.view.adapter.FoodsByCategoryAdapter
import com.reverb.recipechallenge.view.adapter.MealsViewedAdapter
import com.reverb.recipechallenge.viewmodel.CategoryViewModel
import com.reverb.recipechallenge.viewmodel.MealViewModel
import kotlinx.coroutines.flow.collectLatest

class HomeFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private val mealViewModel by viewModels<MealViewModel> ()
    private val categoryViewModel by viewModels<CategoryViewModel> ()

    private val mealsViewedAdapter by lazy { MealsViewedAdapter(){ meal ->  onItemMealSelected(meal)} }
    private val categoriesAdapter by lazy { CategoriesAdapter(){ position -> onCategorySelected(position) } }

    private val foodsByCategoryAdapter by lazy { FoodsByCategoryAdapter(){ mealresume -> onItemResumeMealSelected(mealresume) } }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //call it manually the first time, then with the adapter RV

        initRecentlyViewedMealsRv()
        initCategoriesRv()
        initFoodsByCategoryRv()


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mealViewModel.mealsResponse.collectLatest {
                when(it){
                    is Resource.Loading -> { }
                    is Resource.Success -> {
                        //println("List of Meals: ${it.data}")
                        mealsViewedAdapter.differList.submitList(it.data)

                    }
                    is Resource.Error -> { println("List of ERRORS") }
                    else -> Unit

                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            categoryViewModel.categoriesResponse.collectLatest {
                when(it){
                    is Resource.Loading -> { }
                    is Resource.Success -> {
                        categoriesAdapter.differList.submitList(it.data)
                    }
                    is Resource.Error -> { println("Errors occurred when getting categories") }
                    else -> Unit
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mealViewModel.filteredByCategoryResponse.collectLatest {
                when(it){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        //foodIds.addAll(it.data!!)//LISTA CON LOS IDS DE LA COMIDA Y LA FOTICO
                        foodsByCategoryAdapter.differ.submitList(it.data)
                    }
                    is Resource.Error -> { println(it) }
                    else -> Unit
                }
            }
        }

        binding.tilLookForRecipe.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }


    }//ON VIEW CREATED



    private fun onItemMealSelected(meal: Meal){
        val actions = HomeFragmentDirections.actionHomeFragmentToRecipeFragment(meal.idMeal)
        findNavController().navigate(actions)
    }

    private fun onItemResumeMealSelected(mealResume: MealResume){
        val actions = HomeFragmentDirections.actionHomeFragmentToRecipeFragment(mealResume.idMeal)
        findNavController().navigate(actions)
    }


    private fun onCategorySelected(position: Int){
        mealViewModel.getMealsIdByCategory(categoriesAdapter.differList.currentList[position].strCategory)

        categoriesAdapter.differList.currentList[position].isSelected = !categoriesAdapter.differList.currentList[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
    }


    private fun initFoodsByCategoryRv() {
        binding.rvFoods.apply {
            adapter = foodsByCategoryAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }
    }

    private fun initCategoriesRv() {
        binding.rvFoodCategories.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }

    }

    private fun initRecentlyViewedMealsRv(){
        binding.rvRecentlyViewed.apply {
            adapter = mealsViewedAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }
    }


}//HOME FRAGMENT