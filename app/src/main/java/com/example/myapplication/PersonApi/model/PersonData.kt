package com.example.myapplication.PersonApi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("person_table")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gender: String?,
    var name: String,
    var age: String,
    var pic: String,
    var trophys: String,
    var matches: String,
    var wins: String,
    var size: String,
    var level: String,
)