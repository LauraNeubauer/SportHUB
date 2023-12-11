package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Event
import com.example.myapplication.model.News

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

}