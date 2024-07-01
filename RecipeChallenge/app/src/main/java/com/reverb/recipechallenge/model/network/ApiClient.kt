package com.reverb.recipechallenge.model.network

import com.reverb.recipechallenge.model.datamodel.CategoryFilter
import com.reverb.recipechallenge.model.datamodel.CategoryList
import com.reverb.recipechallenge.model.datamodel.MealList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("search.php")
    suspend fun getRandomMeal(
        @Query("s") name:String
    ): Response<MealList>


    @GET("categories.php")
    suspend fun getAllCategories(): Response<CategoryList>


    @GET("lookup.php")
    suspend fun findMealById(
        @Query("i") mealId: String
    ): Response<MealList>

    /**
    *   Filter By Category will give: meal name, image and mealId
    *   I'll use mealId to get the actual meal :( too long process
    */
    @GET("filter.php")
    suspend fun getFoodIdByCategory(
        @Query("c") category: String
    ): Response<CategoryFilter>

}