package com.example.myapplication.PersonApi.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.PersonApi.model.PersonData

// Datenbankannotation, die die Entitäten und die Version der Datenbank angibt
@Database(entities = [PersonData::class], version = 2)
abstract class PersonDatabase : RoomDatabase() {

    // Abstrakte Methode zum Zugriff auf das DAO
    abstract val personDao: PersonDao
}

// Später initialisiertes Objekt für die Singleton-Instanz der Datenbank
private lateinit var dbInstance: PersonDatabase

// Funktion zum Abrufen der Datenbankinstanz
fun getDatabase(context: Context): PersonDatabase {
    // Synchronisiert den Zugriff auf die Datenbankinstanz, um Thread-Sicherheit zu gewährleisten
    synchronized(PersonDatabase::class.java) {
        if (!::dbInstance.isInitialized) {
            // Erzeugt eine Datenbankinstanz, wenn sie noch nicht initialisiert wurde
            dbInstance = Room.databaseBuilder(
                context.applicationContext,
                PersonDatabase::class.java,
                "person_database"
            ).build()
        }
        return dbInstance
    }
}
