package com.example.myapplication.api.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.api.model.PersonData


@Database(entities = [PersonData::class], version = 2)
abstract class PersonDatabase : RoomDatabase() {

    abstract val personDao: PersonDao
}

private lateinit var dbInstance: PersonDatabase

fun getDatabase(context: Context): PersonDatabase {
    synchronized(PersonDatabase::class.java) {
        if (!::dbInstance.isInitialized) {
            dbInstance = Room.databaseBuilder(
                context.applicationContext,
                PersonDatabase::class.java,
                "person_database"
            ).build()
        }
        return dbInstance
    }
}
