package com.reverb.mvvmex.domain

import com.reverb.mvvmex.data.model.QuoteModel
import com.reverb.mvvmex.data.model.QuoteProvider
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor( private val quoteProvider: QuoteProvider){

    operator fun invoke(): QuoteModel?{
        val quotes = quoteProvider.quotes
        if( !quotes.isNullOrEmpty() ){
            val random = (quotes.indices).random()
            return quotes[random]
        }
        return null
    }

}