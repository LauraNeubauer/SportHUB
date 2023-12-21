package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.PersonApi.Repository
import com.example.myapplication.PersonApi.local.getDatabase
import com.example.myapplication.model.Chat

class MainViewModel(application: Application) : AndroidViewModel(application) {

        private val database = getDatabase(application)
        private val repo = Repository(database)

    private val _currentChat = MutableLiveData<Chat>()

    val currentChat: MutableLiveData<Chat>
        get() = _currentChat

    fun setCurrentChat(chat: Chat) {
        _currentChat.postValue(chat)
    }
}