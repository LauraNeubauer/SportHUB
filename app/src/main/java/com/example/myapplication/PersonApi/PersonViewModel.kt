package com.example.myapplication.PersonApi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.PersonApi.local.getDatabase
import com.example.myapplication.PersonApi.model.PersonData
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repo = Repository(database)

    val contacts = repo.personenListe

    private val _currentProfile = MutableLiveData<PersonData>()
    val currentProfile: MutableLiveData<PersonData>
        get() = _currentProfile

    fun setCurrentProfile(profile: PersonData) {
        _currentProfile.postValue(profile)
    }

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
