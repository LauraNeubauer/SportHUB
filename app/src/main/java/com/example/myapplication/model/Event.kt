package com.example.myapplication.model

class Event(
    // Datenklasse für die Speicherung der Daten für Events der Vereine
    var name: String,
    var club: String,
    var participants: Int?,
    var level: String?,
    var player: String?,
    var date: String,
    var time: String,
    var place: String,
    var pictureOne: Int,
    var pictureTwo: Int?,
    var pictureThree: Int?,
    var pictureFour: Int?,
    var Match: Boolean,

) {
}