package com.example.myapplication.PersonApi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.PersonApi.local.getChatDatabase
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getChatDatabase(application)
    private val repo = ChatRepository(database)

    val chats = repo.chatList

    private val _currentChat = MutableLiveData<Int>()

    val currentChat: MutableLiveData<Int>
        get() = _currentChat


    fun setCurrentChat(chat: Int) {
        _currentChat.postValue(chat)
    }

    fun loadChats() {
        viewModelScope.launch {
            repo.getChat()
            Log.d("TAG", "Chats ausgegeben")
        }
    }
}