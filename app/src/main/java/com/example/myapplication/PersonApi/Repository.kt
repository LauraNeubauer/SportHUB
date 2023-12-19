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
        val numberOfPersons = 100
        val personList = mutableListOf<Person>()

        repeat(numberOfPersons) {
            val response = PersonApi.retrofitService.getPerson()
            personList.addAll(response.results)

            val person = response.results.first()

            val winPercentage = (10..60).random()

            val personFullName = "${person.name.first} ${person.name.last}"
            val personAge = person.dob.age
            val personPicture = person.picture.large
            val personMatches = person.location.street.number
            val personTrophys = person.registered.age
            val personWins = ((winPercentage / 100.0) * personMatches).coerceIn(1.0, personMatches.toDouble()).toInt()
            val personSize =
                if (person.gender == "female") {
                    (156..176).random()
                } else {
                    (168 .. 210).random()
                }
            val personLevel =
                if (personWins in (1..20)) {
                    "BEGINNER"
                } else if (personWins in (20..80)) {
                    "IMPROVER"
                } else if (personWins in (80..150)) {
                    "ADVANCED"
                } else if (personWins in (150..200)) {
                    "PRACTITIONER"
                } else {
                    "EXPERT"
                }

            if (!isArabicName(personFullName) &&
                personFullName.length <= 18 &&
                personAge in 16..65 &&
                personTrophys in 1..999 &&
                personMatches in 1..999
            ) {
                val personData = PersonData(
                    name = personFullName,
                    gender = person.gender,
                    age = personAge.toString(),
                    pic = personPicture,
                    trophys = personTrophys.toString(),
                    matches = personMatches.toString(),
                    wins = personWins.toString(),
                    size = personSize.toString(),
                    level = personLevel
                )
                db.personDao.insertPerson(personData)
                Log.d("TAG", "Person in die Liste geladen")
            }
        }
    }

    fun isArabicName(name: String): Boolean {
        val arabicRegex = Regex("[\\p{InArabic}]+")
        return arabicRegex.containsMatchIn(name)
    }


}
