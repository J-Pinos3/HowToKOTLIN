package com.reverb.recipechallenge.model.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reverb.recipechallenge.model.datamodel.MealEntity

import kotlinx.parcelize.Parcelize


@Entity(tableName = "favorite_meal")
@Parcelize
data class MealEntityResponse(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "mealId") val mealId:String,
    @ColumnInfo(name = "mealName") val mealName: String,
    @ColumnInfo(name = "mealImage") val mealImage: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
): Parcelable


fun MealEntity.toMealResponse() =
    MealEntityResponse(
        mealId = mealId,
        mealName = mealName,
        mealImage = mealImage,
        isFavorite = isFavorite
    )