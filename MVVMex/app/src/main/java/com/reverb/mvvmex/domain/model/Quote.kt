package com.reverb.mvvmex.domain.model

import com.reverb.mvvmex.data.database.dao.QuoteDao
import com.reverb.mvvmex.data.database.entities.QuoteEntity
import com.reverb.mvvmex.data.model.QuoteModel

data class Quote(val quote: String, val author:String)

fun QuoteModel.toDomain() = Quote(quote, author)

fun QuoteEntity.toDomain() = Quote(quote, author)
