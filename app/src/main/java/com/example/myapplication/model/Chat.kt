package com.example.myapplication.model

class Chat(
    var groupID: Int = 0,
    var groupName: String = "",
    var groupPic: Int = 0,
    val messages: MutableList<Message> = mutableListOf()
)
