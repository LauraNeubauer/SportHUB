package com.example.myapplication.model

data class Message(
    var loc: Int = 0,
    var text : String,
    val from : String,
    val timestamp: String,
    val send: Boolean
)
