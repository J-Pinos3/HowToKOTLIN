package com.reverb.recipechallenge.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reverb.recipechallenge.model.database.dao.MealDao
import com.reverb.recipechallenge.model.database.entities.MealEntityResponse

@Database(entities = [MealEntityResponse::class], version = 1)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}