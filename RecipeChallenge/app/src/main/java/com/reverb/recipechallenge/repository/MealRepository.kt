package com.reverb.recipechallenge.repository

import android.util.Log
import com.reverb.recipechallenge.model.core.RetrofitHelper
import com.reverb.recipechallenge.model.datamodel.Meal
import com.reverb.recipechallenge.model.datamodel.MealList
import retrofit2.Response

class MealRepository {

    suspend fun getRandomMealRep() =  RetrofitHelper.consumeAPI.getRandomMeal("ec")


}