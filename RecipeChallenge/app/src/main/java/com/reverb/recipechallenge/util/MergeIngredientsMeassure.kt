package com.reverb.recipechallenge.util

import android.text.SpannableString
import androidx.core.text.toSpannable
import com.reverb.recipechallenge.model.datamodel.Meal

fun mergeMealIngredientsMeasure(meal: Meal): String{

    var ingredientsAndMeasures = ""


    val ingredients = getIngredients(meal)
    val measures = getMeasures(meal = meal)
    for ( index in 0 until ingredients.size ){
        if(   ( !(measures[index].isNullOrBlank()) ) && ( !(ingredients[index].isNullOrBlank()) )   ){
            ingredientsAndMeasures += (" • " + measures[index] + " " +  ingredients[index] + "\n")
        }
    }

    return ingredientsAndMeasures
}

private fun getIngredients(meal: Meal): MutableList<String>{

    val ingredientsList = mutableListOf<String>()

    ingredientsList.add(meal.strIngredient1 ?: "")
    ingredientsList.add(meal.strIngredient2 ?: "")
    ingredientsList.add(meal.strIngredient3 ?: "")
    ingredientsList.add(meal.strIngredient4 ?: "")
    ingredientsList.add(meal.strIngredient5 ?: "")
    ingredientsList.add(meal.strIngredient6 ?: "")
    ingredientsList.add(meal.strIngredient7 ?: "")
    ingredientsList.add(meal.strIngredient8 ?: "")
    ingredientsList.add(meal.strIngredient9 ?: "")
    ingredientsList.add(meal.strIngredient10 ?: "")
    ingredientsList.add(meal.strIngredient11 ?: "")
    ingredientsList.add(meal.strIngredient12 ?: "")
    ingredientsList.add(meal.strIngredient13 ?: "")
    ingredientsList.add(meal.strIngredient14 ?: "")
    ingredientsList.add(meal.strIngredient15 ?: "")
    ingredientsList.add(meal.strIngredient16 ?: "")
    ingredientsList.add(meal.strIngredient17 ?: "")
    ingredientsList.add(meal.strIngredient18 ?: "")
    ingredientsList.add(meal.strIngredient19 ?: "")
    ingredientsList.add(meal.strIngredient20 ?: "")

    return ingredientsList
}

private fun getMeasures(meal: Meal): MutableList<String>{

    val measuresList = mutableListOf<String>()

    measuresList.add(meal.strMeasure1 ?: "")
    measuresList.add(meal.strMeasure2 ?: "")
    measuresList.add(meal.strMeasure3 ?: "")
    measuresList.add(meal.strMeasure4 ?: "")
    measuresList.add(meal.strMeasure5 ?: "")
    measuresList.add(meal.strMeasure6 ?: "")
    measuresList.add(meal.strMeasure7 ?: "")
    measuresList.add(meal.strMeasure8 ?: "")
    measuresList.add(meal.strMeasure9 ?: "")
    measuresList.add(meal.strMeasure10 ?: "")
    measuresList.add(meal.strMeasure11 ?: "")
    measuresList.add(meal.strMeasure12 ?: "")
    measuresList.add(meal.strMeasure13 ?: "")
    measuresList.add(meal.strMeasure14 ?: "")
    measuresList.add(meal.strMeasure15 ?: "")
    measuresList.add(meal.strMeasure16 ?: "")
    measuresList.add(meal.strMeasure17 ?: "")
    measuresList.add(meal.strMeasure18 ?: "")
    measuresList.add(meal.strMeasure19 ?: "")
    measuresList.add(meal.strMeasure20 ?: "")

    return measuresList
}