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
    var sportsOne: String,
    var sportsTwo: String?,
    val bio: String,
    var club: Int,
    var chat: String,
    var chattwo: String,
    val messagewritten: String,
    val message: String,
    val messagetwowritten: String,
    val messagetwo: String,
)