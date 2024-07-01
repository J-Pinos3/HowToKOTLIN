package com.reverb.mvvmex.domain

import com.reverb.mvvmex.data.QuoteRepository
import com.reverb.mvvmex.data.database.entities.toDatabaseEntity
import com.reverb.mvvmex.data.model.QuoteModel
import com.reverb.mvvmex.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
){

    //private val repository = QuoteRepository()

    //this execures at the beginning of the app
    suspend operator fun invoke(): List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes = quotes.map { it.toDatabaseEntity() })
            quotes
        }
        else{
            //if cannot call server api, use db
            repository.getAllQuotesFromDataBase()
        }
    }


}