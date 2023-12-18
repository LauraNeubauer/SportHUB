package com.example.myapplication.PersonApi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("person_table")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var age: String
)