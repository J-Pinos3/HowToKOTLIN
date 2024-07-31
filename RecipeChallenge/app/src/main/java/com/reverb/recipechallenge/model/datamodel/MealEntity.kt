package com.reverb.recipechallenge.model.datamodel

import com.reverb.recipechallenge.model.database.entities.MealEntityResponse

data class MealEntity(
    val mealId:String = "",
    val mealName: String = "",
    val mealImage: String = "",
    val isFavorite: Boolean = false
)

fun MealEntityResponse.toMealEntity() =
    MealEntity(
        mealId = mealId,
        mealName = mealName,
        mealImage = mealImage,
        isFavorite = isFavorite  )