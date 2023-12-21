package com.example.myapplication.model

class Chat(
    var groupName: String,
    var lastMessageFrom: String,
    var lastMessage: String,
    var lastMessageDay: String,
    var groupPic: Int,
    var messages: List<Message>?
)