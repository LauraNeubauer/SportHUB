package com.example.myapplication.model

class Chat(
    // Datenklasse für die Repräsentation eines Chat-Gruppenobjekts
    var groupName: String = "",
    var groupPic: Int = 0,
    var messages: MutableList<Message> = mutableListOf(),
    var groupID: String? = "",
)
