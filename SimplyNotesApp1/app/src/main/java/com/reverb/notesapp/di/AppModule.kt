package com.reverb.notesapp.di

import android.content.Context
import androidx.room.Room
import com.reverb.notesapp.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
THIS CLASS WILL HOLD FUNCTIONS THAT WILL
CREATE THE DEPENDENCIES THAT IM GONNA USE


SingletonComponent = instances get destroyed as soon the application gets destroyed
*/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton //singleton cuz we only need 1 instance of the db
    fun provideRoomDataBase(@ApplicationContext context: Context)
        = Room.databaseBuilder(context, NoteDatabase::class.java, "NoteDataBase")
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()

}