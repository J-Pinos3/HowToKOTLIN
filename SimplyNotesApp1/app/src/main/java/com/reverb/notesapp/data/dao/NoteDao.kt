package com.reverb.notesapp.data.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.reverb.notesapp.data.entity.Note
import kotlinx.coroutines.flow.Flow


/*
Contains the sql methods that my app can use
*/

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}