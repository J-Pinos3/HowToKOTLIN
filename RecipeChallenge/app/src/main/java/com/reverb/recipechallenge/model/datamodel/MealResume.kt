package com.reverb.recipechallenge.model.datamodel

data class MealResume(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

fun MealResume.toMealEntity() =
    MealEntity(
        mealId = idMeal,
        mealName = strMeal,
        mealImage = strMealThumb,
        isFavorite = false
    )