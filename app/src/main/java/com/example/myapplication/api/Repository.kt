package com.example.myapplication.api

import android.util.Log
import com.example.myapplication.api.local.PersonDatabase
import com.example.myapplication.api.model.Person
import com.example.myapplication.api.model.PersonData


class Repository(
    private val db: PersonDatabase
) {
    val personenListe = db.personDao.getAll()

    suspend fun getPerson() {
        val numberofPersons = 1
        val PersonList = mutableListOf<Person>()

        repeat(numberofPersons) {
            val response = PersonApi.retrofitService.getPerson()
            PersonList.addAll(response.results)

            val person = response.results.first()
            val personData = PersonData(name = person.name.first + " " + person.name.last, age = person.dob.age.toString())
            db.personDao.insertPerson(personData)
            Log.d("TAG", "Person in die Liste geladen")
        }
    }
}
