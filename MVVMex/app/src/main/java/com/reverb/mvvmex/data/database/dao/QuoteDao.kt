package com.reverb.mvvmex.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.reverb.mvvmex.data.database.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table ORDER BY Author DESC")
    suspend fun getAllQuotes(): List<QuoteEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuoteAll(quoteEntity: List<QuoteEntity>)

    @Query("DELETE FROM quote_table")
    suspend fun deleteAllQuotes()
}