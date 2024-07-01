package com.reverb.mvvmex.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reverb.mvvmex.domain.model.Quote

@Entity(tableName = "quote_table")
data class QuoteEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")val id: Int = 0,
    @ColumnInfo(name = "Quote") val quote: String,
    @ColumnInfo(name = "Author") val author: String
)

fun Quote.toDatabaseEntity() = QuoteEntity(quote = quote, author = author)