package com.reverb.mvvmex.data

import com.reverb.mvvmex.data.model.QuoteModel
import com.reverb.mvvmex.data.model.QuoteProvider
import com.reverb.mvvmex.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
) {

    //private val api = QuoteService()
    suspend fun getAllQuotes(): List<QuoteModel>{
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }

}