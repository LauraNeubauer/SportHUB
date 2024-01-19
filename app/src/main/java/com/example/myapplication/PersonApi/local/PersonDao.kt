package com.example.myapplication.PersonApi.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.PersonApi.model.PersonData

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(personData: PersonData)

    @Query("SELECT * FROM person_table")
    fun getAll(): LiveData<MutableList<PersonData>>

}
