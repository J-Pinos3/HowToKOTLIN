package com.reverb.recipechallenge.repository

import com.reverb.recipechallenge.model.database.dao.MealDao
import com.reverb.recipechallenge.model.database.entities.MealEntityResponse
import com.reverb.recipechallenge.model.database.entities.toMealResponse
import com.reverb.recipechallenge.model.datamodel.MealEntity
import com.reverb.recipechallenge.model.datamodel.toMealEntity
import javax.inject.Inject

class DataBaseRepository @Inject constructor(
    private val mealDao: MealDao
) {

    suspend fun getAllFavoritesFromDB(): List<MealEntity>{
        val response: List<MealEntityResponse> = mealDao.getAllFavoriteMeals()
        return response.map {
            it.toMealEntity()
        }
    }

    suspend fun addNewFavoriteMeal(mealEntity: MealEntity){
        val mealEntityResponse = mealEntity.toMealResponse()
        mealDao.insertFavoriteMeal(mealEntityResponse)
    }

    suspend fun deleteFavoriteMeal(idMeal: String){
        mealDao.deleteFromFavorites(idMeal)
    }

    suspend fun getFavorietMealById(idMeal: String): MealEntityResponse?{
        return mealDao.getFavoriteMealById(idMeal)
    }

}