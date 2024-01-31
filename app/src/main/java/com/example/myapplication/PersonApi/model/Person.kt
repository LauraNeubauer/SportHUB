package com.example.myapplication.PersonApi.model

// Datenklasse, die Informationen über eine Person enthält
data class Person(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Registered,
    val phone: String,
    val cell: String,
    val id: Id,
    val picture: Picture,
    val nat: String
)

// Datenklasse für den Namen einer Person
data class Name(
    val title: String,
    val first: String,
    val last: String
)

// Datenklasse für den Wohnort einer Person
data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)

// Datenklasse für die Straßeninformationen
data class Street(
    val number: Int,
    val name: String
)

// Datenklasse für die Koordinaten
data class Coordinates(
    val latitude: String,
    val longitude: String
)

// Datenklasse für die Zeitzone
data class Timezone(
    val offset: String,
    val description: String
)

// Datenklasse für den Login-Bereich
data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

// Datenklasse für das Geburtsdatum
data class Dob(
    val date: String,
    val age: Int
)

// Datenklasse für die Registrierungsinformationen
data class Registered(
    val date: String,
    val age: Int
)

// Datenklasse für die Identifikationsinformationen
data class Id(
    val name: String,
    val value: String?
)

// Datenklasse für das Bild einer Person
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
