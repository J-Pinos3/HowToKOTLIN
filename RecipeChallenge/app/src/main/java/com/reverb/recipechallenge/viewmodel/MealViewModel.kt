package com.reverb.recipechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealList
import com.reverb.recipechallenge.model.datamodel.MealResume
import com.reverb.recipechallenge.repository.MealRepository
import com.reverb.recipechallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response

class MealViewModel: ViewModel(){

    private val repository = MealRepository()

    private val _mealsResponse = MutableStateFlow<Resource<List<Meal>>>(Resource.Unspecified())
    val mealsResponse = _mealsResponse.asStateFlow()

    private val _filteredByCaetegoryResponse = MutableStateFlow< Resource<List<MealResume>> >(Resource.Unspecified())
    val filteredByCategoryResponse = _filteredByCaetegoryResponse.asStateFlow()

    private val _mealsById = MutableStateFlow< Resource<Meal> >(Resource.Unspecified())
    val mealsById = _mealsById.asStateFlow()



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

    fun getMealsIdByCategory(category: String){
        viewModelScope.launch { _filteredByCaetegoryResponse.emit(Resource.Loading()) }

        viewModelScope.launch{
            val response = repository.getFoodIdByCategoryRep(category)
            if(response.isSuccessful && response.body()?.meals != null){
                response.body()?.meals.let {
                    _filteredByCaetegoryResponse.emit(Resource.Success(it!!))
                }
            }else{
                _filteredByCaetegoryResponse.emit(Resource.Error("There's no list by category"))
            }
        }
    }

    //TODO: MOVE TO RECIPE VIEWMODEL
    fun getMealById(mealId: String){
        viewModelScope.launch { _mealsById.emit(Resource.Loading()) }
        var errorMessage = ""
        viewModelScope.launch {

            val response = repository.getMealRepById(mealId)
            if(response.isSuccessful && response.body()?.meals != null){
                response!!.body()?.meals?.let {
                    _mealsById.emit(Resource.Success( it[0] ))
                }
            }else{
                errorMessage = "There's no food with that Id"
                _mealsById.emit(Resource.Error(errorMessage))
            }


        }
    }




}//MEAL VIEW MODEL