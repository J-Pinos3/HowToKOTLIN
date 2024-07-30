package com.reverb.recipechallenge.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reverb.recipechallenge.model.database.entities.MealEntityResponse

@Dao
interface MealDao {

    @Query("SELECT * FROM favorite_meal")
    suspend fun getAllFavoriteMeals(): List<MealEntityResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMeal( mealFavoriteEntity: MealEntityResponse)

    @Delete
    suspend fun deleteFromFavorites(mealFavoriteEntity: MealEntityResponse)


    @Query("SELECT * FROM favorite_meal WHERE mealId LIKE :idMeal LIMIT 1")
    suspend fun getFavoriteMealById(idMeal: String): MealEntityResponse?

}