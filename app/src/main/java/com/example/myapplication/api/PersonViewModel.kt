package com.example.myapplication.api

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.local.getDatabase
import com.example.myapplication.model.ExamplePerson
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repo = Repository(database)
    val personList = repo.personenListe

    var _singleExamplePerson = MutableLiveData<ExamplePerson>()

    val single : MutableLiveData<ExamplePerson>
        get() = _singleExamplePerson

    init {
        loadPerson()
    }

    fun selectedPerson(examplePerson: ExamplePerson) {
        _singleExamplePerson.value = examplePerson
    }

    fun loadPerson() {
        viewModelScope.launch {
            repo.getPerson()
            Log.d("TAG", "Person ausgegeben")
        }
    }
}