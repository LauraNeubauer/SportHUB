package com.example.myapplication.PersonApi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("chat_table")
data class ChatData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var groupname: String,
    var from: Int,
    var pic: String,
    var message: String,
    var time: String,
)