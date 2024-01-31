package com.example.myapplication.PersonApi.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.PersonApi.model.PersonData

@Dao
interface PersonDao {

    // Data Access Object (DAO) für den Zugriff auf die lokale Datenbank
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(personData: PersonData)

    // Abfrage aller in der Datenbank gespeicherten Personen
    @Query("SELECT * FROM person_table")
    fun getAll(): LiveData<MutableList<PersonData>>

}
