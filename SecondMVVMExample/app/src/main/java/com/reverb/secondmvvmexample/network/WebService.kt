package com.reverb.secondmvvmexample.network

import com.reverb.secondmvvmexample.network.response.PeliculasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WebService {

    //@Headers("Content-type:application/json")
    @GET("now_playing")
    suspend fun obtenerCartelera(
        @Query("api_key") apiKey: String
    ): Response<PeliculasResponse>


    // @Headers("Content-type:application/json")
    @GET("popular")
    suspend fun obtenerPopulares(
        @Query("api_key") apiKey: String
    ): Response<PeliculasResponse>


}