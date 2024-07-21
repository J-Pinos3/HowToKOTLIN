package com.reverb.recipechallenge.repository

import android.util.Log
import com.reverb.recipechallenge.model.core.RetrofitHelper
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealList
import retrofit2.Response

class MealRepository {

    suspend fun getRandomMealRep() =  RetrofitHelper.consumeAPI.getRandomMeal("ec")

    suspend fun getFoodIdByCategoryRep(category: String) = RetrofitHelper.consumeAPI.getFoodIdByCategory(category)

    suspend fun getMealRepById(mealId: String) = RetrofitHelper.consumeAPI.findMealById(mealId = mealId)

    suspend fun searchMealsByName(mealName: String) = RetrofitHelper.consumeAPI.getRandomMeal(mealName)
}