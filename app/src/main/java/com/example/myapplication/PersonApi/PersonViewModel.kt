package com.example.myapplication.PersonApi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.PersonApi.local.getDatabase
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repo = Repository(database)

    init {
        loadPersons()
    }

    fun loadPersons() {
        viewModelScope.launch {
            repo.getPerson()
            Log.d("TAG", "Person ausgegeben")
        }
    }
}