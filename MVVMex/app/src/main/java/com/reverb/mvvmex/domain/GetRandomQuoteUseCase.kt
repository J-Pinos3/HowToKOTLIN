package com.reverb.mvvmex.domain

import com.reverb.mvvmex.data.QuoteRepository
import com.reverb.mvvmex.data.model.QuoteModel
import com.reverb.mvvmex.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor( private val repository: QuoteRepository){

    suspend operator fun invoke(): Quote?{
        val quotes = repository.getAllQuotesFromDataBase()
        if( !quotes.isNullOrEmpty() ){
            val random = (quotes.indices).random()
            return quotes[random]
        }
        return null
    }

}