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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.reverb.recipechallenge.databinding.SearchFragmentLayoutBinding
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.util.Resource
import com.reverb.recipechallenge.view.adapter.SearchMealsAdapter
import com.reverb.recipechallenge.viewmodel.MealViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class SearchFragment: Fragment() {
    private lateinit var binding: SearchFragmentLayoutBinding

    private val mealViewModel by viewModels<MealViewModel>()

    private val searchMealsAdapter by lazy { SearchMealsAdapter(){meal -> onItemMealSelected(meal) } }

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
                    mealViewModel.searchMealsByName(p0.toString())
                }

            }, 2000L)

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

//        binding.tietLookForRecipe.addTextChangedListener {
//            Log.i("user searches", it.toString())
//        }
        binding.tietLookForRecipe.addTextChangedListener(searchTextWatcher)


        //lifecycleScope.launchWhenStarted { }

        lifecycleScope.launch {
            mealViewModel.searchResponse.collectLatest {
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


    }

    private fun onItemMealSelected(meal: Meal){
        /**
         *  TODO THIS FUNCTION WILL SEND THE MEAL ID TO THE MEAL RECIPE FRAGMENT
         * */
    }


    private fun initSearchedMealsRv() {
        binding.rvSearchedMeals.apply {
            adapter = searchMealsAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
        }
    }

}//SEARCH FRAGMENT