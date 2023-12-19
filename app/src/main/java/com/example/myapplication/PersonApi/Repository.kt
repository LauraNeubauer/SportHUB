package com.example.myapplication.PersonApi

import android.util.Log
import com.example.myapplication.PersonApi.local.PersonDatabase
import com.example.myapplication.PersonApi.model.Person
import com.example.myapplication.PersonApi.model.PersonData


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
            val personData = PersonData(
                name = person.name.first + " " + person.name.last,
                age = person.dob.age.toString(),
                pic = person.picture.large)
            db.personDao.insertPerson(personData)
            Log.d("TAG", "Person in die Liste geladen")
        }
    }
}
