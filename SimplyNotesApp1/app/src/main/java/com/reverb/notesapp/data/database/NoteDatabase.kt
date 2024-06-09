package com.reverb.notesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reverb.notesapp.data.dao.NoteDao
import com.reverb.notesapp.data.entity.Note

@Database(entities =  arrayOf( Note::class), version = 1 )
abstract class NoteDatabase(): RoomDatabase() {

    abstract fun noteDao(): NoteDao

}