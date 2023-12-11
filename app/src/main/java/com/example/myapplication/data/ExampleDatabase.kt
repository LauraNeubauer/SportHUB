package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Event
import com.example.myapplication.model.Match
import com.example.myapplication.model.News
import com.example.myapplication.model.Person

class ExampleDatabase {

    var eventList : List<Event> = listOf(
            Event("Holstein Cup", "Friedländer CLT", 6, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 3, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 2, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 3, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 2, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 4, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
        )
        fun loadEvents() : List<Event>{
            return eventList
        }

    var newsList: List<News> = listOf(
        News("Holstein Cup: Neue Sponsoren gewonnen", "Zwei Sponsoren unterstützen die Veranstaltung, um sie auf ein neues Niveau zu heben."),
        News("Holstein Cup Ergebnisse veröffentlicht", "Die Gewinner und Ergebnisse sind jetzt verfügbar. Herzlichen Glückwunsch an alle Teilnehmer."),
        News("Wichtige Änderung am Veranstaltungsort", "Der Austragungsort des Holstein Cups wurde geändert. Bitte beachten Sie die Aktualisierungen."),
        News("Holstein Cup: Rekordteilnahme", "Die diesjährige Ausgabe verzeichnet die höchste Teilnehmerzahl. Vielen Dank an die unterstützende Community."),
        News("Interview mit Holstein Cup Champion", "Exklusives Interview mit dem diesjährigen Gewinner. Erfahren Sie mehr über seine Vorbereitungen und Gefühle."),
        News("Neue Jugendkategorie beim Holstein Cup", "Die Veranstaltung erweitert das Teilnehmerfeld um eine Jugendkategorie zur Förderung junger Talente."),
    )


    fun loadNews() : List<News> {
        return newsList
    }

    var personList: List<Person> = listOf(
        Person("Anna", "Friedel", "22", "Beginner", "23", 12, 18, "Hockeyclub Hamburg"),
        Person("Max", "Mustermann", "30", "Intermediate", "42", 10, 15, "Football Club"),
        Person("Lena", "Schmidt", "25", "Advanced", "55", 8, 20, "Tennis Club"),
        Person("Jan", "Müller", "28", "Beginner", "37", 14, 16, "Chess Club"),
        Person("Sophie", "Schneider", "33", "Advanced", "61", 9, 22, "Swimming Club"),
    )

    fun loadPersons() : List<Person> {
        return personList
    }

    var matchList : List<Match> = listOf(
        Match(personList[0], false),
        Match(personList[1], false),
        Match(personList[2], false),
        Match(personList[3], false),
        Match(personList[4], false),
    )

    fun loadMatches() : List<Match> {
        return matchList
    }
}