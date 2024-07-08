package com.reverb.recipechallenge.view.fragments

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.reverb.recipechallenge.databinding.HomeFragmentLayoutBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.util.Resource
import com.reverb.recipechallenge.view.adapter.CategoriesAdapter
import com.reverb.recipechallenge.view.adapter.MealsViewedAdapter
import com.reverb.recipechallenge.viewmodel.CategoryViewModel
import com.reverb.recipechallenge.viewmodel.MealViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private val mealViewModel by viewModels<MealViewModel> ()
    private val categoryViewModel by viewModels<CategoryViewModel> ()
    private val mealsViewedAdapter by lazy { MealsViewedAdapter(){ meal ->  onItemMealSelected(meal)} }
    private val categoriesAdapter by lazy { CategoriesAdapter(){ position -> onCategorySelected(position) } }

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

        initRecentlyViewedMealsRv()
        initCategoriesRv()

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

    }//ON VIEW CREATED



    private fun onItemMealSelected(meal: Meal){
        /**
         * TODO THIS FUNCTION WILL SEND THE MEAL TO THE MEAL RECIPE FRAGMENT
         *
         * */
    }

    private fun onCategorySelected(position: Int){
        categoriesAdapter.differList.currentList[position].isSelected = !categoriesAdapter.differList.currentList[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
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