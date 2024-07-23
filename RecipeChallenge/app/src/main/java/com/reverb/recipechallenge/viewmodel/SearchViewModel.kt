package com.reverb.recipechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.recipechallenge.model.datamodel.Category
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealResume
import com.reverb.recipechallenge.repository.CategoriesRepository
import com.reverb.recipechallenge.repository.MealRepository
import com.reverb.recipechallenge.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val repositoryCategories = CategoriesRepository()
    private val repositoryMeals = MealRepository()

    private val _categoriesResponse = MutableStateFlow<Resource<List<Category>>>(Resource.Unspecified())
    val categoriesResponse = _categoriesResponse.asStateFlow()

    private val _searchMealResponse = MutableStateFlow<Resource<List<Meal>>>(Resource.Unspecified())
    val searchMealResponse = _searchMealResponse.asStateFlow()

    private val _filteredByCategoryResponse = MutableStateFlow< Resource<List<MealResume>> >(Resource.Unspecified())
    val filteredByCategoryResponse = _filteredByCategoryResponse.asStateFlow()


    fun getListOfCategories(){
        viewModelScope.launch {
            _categoriesResponse.emit(Resource.Loading())

            val response = repositoryCategories.getAllMealCategories()
            if(response.isSuccessful && response.body()?.categories != null){
                response.body()?.categories.let {
                    _categoriesResponse.emit(Resource.Success(it!!))
                }
            }else{
                _categoriesResponse.emit(Resource.Error("There's no categories list"))
            }
        }
    }

    fun searchMealsByName(mealName: String){
        viewModelScope.launch {
            _searchMealResponse.emit(Resource.Loading())
            val response = repositoryMeals.searchMealsByName(mealName)
            if( response.isSuccessful && response.body()?.meals != null ){
                response.body()?.meals.let {
                    _searchMealResponse.emit(Resource.Success(it!!))
                }
            }else{
                _searchMealResponse.emit(Resource.Error("Couldn't find a meal with that name"))
            }
        }
    }


    fun getMealsIdByCategory(category: String){
        viewModelScope.launch { _filteredByCategoryResponse.emit(Resource.Loading()) }
        viewModelScope.launch{
            val response = repositoryMeals.getFoodIdByCategoryRep(category)
            if(response.isSuccessful && response.body()?.meals != null){
                response.body()?.meals.let {
                    _filteredByCategoryResponse.emit(Resource.Success(it!!))
                }
            }else{
                _filteredByCategoryResponse.emit(Resource.Error("There's no list by category"))
            }
        }
    }

}//SEARCH VIEW MODEL