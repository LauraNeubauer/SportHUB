package com.example.myapplication.model

data class Message(
    // Datenklasse für die Speicherung der Daten für Nachrichten innerhalb eines Chats
    var text : String,
    val from : String,
    val timestamp: String,
    val send: Boolean
)
