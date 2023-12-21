package com.example.myapplication.PersonApi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.PersonApi.local.getChatDatabase
import com.example.myapplication.PersonApi.model.ChatData
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getChatDatabase(application)
    private val repo = ChatRepository(database)

    val chats = repo.chatList

    private val _currentChat = MutableLiveData<ChatData>()
    private var _currentGroup: Int = 0
    val currentChat: MutableLiveData<ChatData>
        get() = _currentChat


    fun setCurrentChat(chat: ChatData) {
        _currentChat.postValue(chat)
    }

    fun loadChats() {
        viewModelScope.launch {
            repo.getChat()
            Log.d("TAG", "Chats ausgegeben")
        }
    }
}