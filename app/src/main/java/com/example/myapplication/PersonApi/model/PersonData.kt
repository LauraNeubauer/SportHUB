package com.example.myapplication.PersonApi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

@Entity("person_table")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @DocumentId
    var uid: String? = "",

    val email: String? = "",
    val pw : String? = "",
    val gender: String? = "",
    var name: String = "",
    var age: String = "",
    var pic: String = "",
    var trophys: String = "",
    var matches: String = "",
    var wins: String = "",
    var size: String = "",
    var level: String = "",
    var sportsOne: String = "",
    var sportsTwo: String = "",
    val bio: String = "",
    var club: Int? = 0,
    var chat: String = "",
)