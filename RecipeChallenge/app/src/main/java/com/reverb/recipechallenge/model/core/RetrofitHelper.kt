package com.reverb.recipechallenge.model.core

import com.reverb.recipechallenge.model.network.ApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val consumeAPI = retrofit.create(ApiClient::class.java)
}