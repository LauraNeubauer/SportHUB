package com.example.myapplication.model

class Chat(
    var groupName: String = "",
    var groupPic: Int = 0,
    var messages: MutableList<Message> = mutableListOf(),
    var groupID: String? = "",
)
