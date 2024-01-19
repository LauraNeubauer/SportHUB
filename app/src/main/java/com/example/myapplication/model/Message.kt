package com.example.myapplication.model

data class Message(
    var text : String,
    val from : String,
    val timestamp: String,
    val send: Boolean
)
