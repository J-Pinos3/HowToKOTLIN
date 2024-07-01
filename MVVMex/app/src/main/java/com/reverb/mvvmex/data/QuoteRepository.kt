package com.reverb.mvvmex.data

import com.reverb.mvvmex.data.database.dao.QuoteDao
import com.reverb.mvvmex.data.database.entities.QuoteEntity
import com.reverb.mvvmex.data.model.QuoteModel
import com.reverb.mvvmex.data.network.QuoteService
import com.reverb.mvvmex.domain.model.Quote
import com.reverb.mvvmex.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao

) {

    //private val api = QuoteService()
    suspend fun getAllQuotesFromApi(): List<Quote>{
        val response: List<QuoteModel> = api.getQuotes()

        return response.map {quoteModel ->
            quoteModel.toDomain()
        }
    }

    suspend fun getAllQuotesFromDataBase(): List<Quote>{
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>){
        quoteDao.insertQuoteAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }

}