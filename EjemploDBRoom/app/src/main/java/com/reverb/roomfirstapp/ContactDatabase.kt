package com.reverb.roomfirstapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reverb.roomfirstapp.Contact
import com.reverb.roomfirstapp.ContactDao

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val dao: ContactDao

}