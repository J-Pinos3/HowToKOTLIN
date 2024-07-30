package com.reverb.recipechallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reverb.recipechallenge.model.database.dao.MealDao
import com.reverb.recipechallenge.model.datamodel.MealEntity
import com.reverb.recipechallenge.model.datamodel.toMealEntity
import com.reverb.recipechallenge.repository.DataBaseRepository
import com.reverb.recipechallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val mealDao: MealDao
): ViewModel() {

    val favoriteMealsChannel = Channel<FavoriteMealsEvents>()
    val mealEvents = favoriteMealsChannel.receiveAsFlow()

    private val favoritesRepository = DataBaseRepository(mealDao)

    private val _allFavoriteMeals = MutableStateFlow<Resource<List<MealEntity>>>(Resource.Unspecified())
    val allFavoriteMeals = _allFavoriteMeals.asStateFlow()

    private val _mealById = MutableStateFlow<Resource<MealEntity>>(Resource.Unspecified())
    val mealById = _mealById.asStateFlow()

    // Get all meals
    fun favoriteMeals(){
        viewModelScope.launch {
            _allFavoriteMeals.emit(Resource.Loading())

            val favoriteMeals = favoritesRepository.getAllFavoritesFromDB()
            if( favoriteMeals.isNotEmpty() ){
                _allFavoriteMeals.emit( Resource.Success(favoriteMeals) )
            }else{
                _allFavoriteMeals.emit( Resource.Error("There's no favorites meals") )
            }
        }
    }

    //get meal by id
    fun favoriteMealByiD(idMeal: String){
        viewModelScope.launch {
            _mealById.emit(Resource.Loading())

            val mealByIdResponse = favoritesRepository.getFavorietMealById(idMeal)
            if( mealByIdResponse != null ){
                _mealById.emit( Resource.Success(mealByIdResponse.toMealEntity()) )
            }else{
                _mealById.emit( Resource.Error("There's no favorite meal with that Id") )
            }
        }
    }


    fun insertfavoriteMeal(meal: MealEntity){
        viewModelScope.launch {
            favoritesRepository.addNewFavoriteMeal(meal)
            favoriteMealsChannel.send( FavoriteMealsEvents.NavigateToFavoritesMeals )
            //after insert navigate to favorites fragment
        }
    }

    fun deleteFavoriteMeal(meal: MealEntity){
        viewModelScope.launch {
            favoritesRepository.deleteFavoriteMeal(meal)
            favoriteMealsChannel.send( FavoriteMealsEvents.ShowUndoSnackBar("Favorite Meal Deleted", meal) )
        }
    }

    sealed class FavoriteMealsEvents{
        data class ShowUndoSnackBar(
            val msg: String,
            val meal: MealEntity
        ): FavoriteMealsEvents()

        object NavigateToFavoritesMeals: FavoriteMealsEvents()
    }

}