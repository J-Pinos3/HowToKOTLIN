package com.reverb.mvvmex.domain

import com.reverb.mvvmex.data.QuoteRepository
import com.reverb.mvvmex.data.model.QuoteModel
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
){

    //private val repository = QuoteRepository()

    suspend operator fun invoke(): List<QuoteModel>? =  repository.getAllQuotes()


}