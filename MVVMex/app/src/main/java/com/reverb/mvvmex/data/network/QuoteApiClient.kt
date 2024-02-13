package com.reverb.mvvmex.data.network

import com.reverb.mvvmex.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {

    @GET("/.json")
    suspend fun getAllQuotes(): Response< List<QuoteModel> >
}