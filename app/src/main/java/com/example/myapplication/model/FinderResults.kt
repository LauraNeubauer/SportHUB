package com.example.myapplication.model

import com.example.myapplication.PersonApi.model.PersonData

class FinderResults(
    var personendaten: PersonData,
    var sport: String,
    var text: String,
    var entfernung: String,
) {
}