package com.reverb.recipechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealList
import com.reverb.recipechallenge.repository.MealRepository
import com.reverb.recipechallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MealViewModel: ViewModel(){

    private val repository = MealRepository()

    private val _mealsResponse = MutableStateFlow<Resource<List<Meal>>>(Resource.Unspecified())
    val mealsResponse = _mealsResponse.asStateFlow()

    init {
        getListOfMeals()
    }

    private fun getListOfMeals(){
        viewModelScope.launch { _mealsResponse.emit(Resource.Loading()) }
        viewModelScope.launch{
            val response = repository.getRandomMealRep()
            if(response.isSuccessful && response.body()?.meals != null){
                response.body()?.meals.let {
                    _mealsResponse.emit(Resource.Success(it!!))
                }
            }else{
                _mealsResponse.emit(Resource.Error("There's no meals list"))
            }
        }
    }


}