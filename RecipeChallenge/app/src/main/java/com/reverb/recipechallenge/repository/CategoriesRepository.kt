package com.reverb.recipechallenge.repository

import com.reverb.recipechallenge.model.core.RetrofitHelper

class CategoriesRepository {

    suspend fun getAllMealCategories() = RetrofitHelper.consumeAPI.getAllCategories()

}