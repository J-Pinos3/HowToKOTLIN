package com.reverb.recipechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.recipechallenge.model.datamodel.Category
import com.reverb.recipechallenge.repository.CategoriesRepository
import com.reverb.recipechallenge.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Response

class CategoryViewModel: ViewModel() {
    private val repository =  CategoriesRepository()

    private val _categoriesResponse = MutableStateFlow<Resource<List<Category>>>(Resource.Unspecified())
    val categoriesResponse = _categoriesResponse.asStateFlow()

    init {
        getListOfCategories()
    }

    private fun getListOfCategories(){
        viewModelScope.launch {  _categoriesResponse.emit(Resource.Loading())  }

        viewModelScope.launch {
            val response = repository.getAllMealCategories()
            if(response.isSuccessful && response.body()?.categories != null){
                response.body()?.categories.let {
                    _categoriesResponse.emit(Resource.Success(it!!))
                }
            }else{
                _categoriesResponse.emit(Resource.Error("There's no categories list"))
            }
        }
    }
}