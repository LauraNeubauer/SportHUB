package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Chat
import com.example.myapplication.model.Event
import com.example.myapplication.model.News
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random


class ExampleDatabase {

    // Funktion zur Generierung eines zufälligen Datums in der Zukunft
    fun generateRandomDate(maxTageInDerZukunft: Long): String {
        val heute = LocalDate.now()
        val zufaelligeAnzahlTage = ThreadLocalRandom.current().nextLong(1, maxTageInDerZukunft + 1)
        val zukunftsDatum = heute.plus(zufaelligeAnzahlTage, ChronoUnit.DAYS)

        // Datumsformat festlegen (dd.MM.yy)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")

        // Datum in das gewünschte Format konvertieren und als String zurückgeben
        return zukunftsDatum.format(formatter)
    }

    fun generateRandomTime(): String {
        // Aktuelle Uhrzeit erhalten
        val currentTime = Calendar.getInstance()

        // Zufällige Stunde zwischen 7 und 23 auswählen
        val randomHour = Random.nextInt(7, 24)
        // Zufällige Minute aus den erlaubten Werten auswählen (00, 15, 30, 45)
        val randomMinute = listOf(0, 15, 30, 45).random()

        currentTime.set(Calendar.HOUR_OF_DAY, randomHour)
        currentTime.set(Calendar.MINUTE, randomMinute)

        // Uhrzeit in das gewünschte Format umwandeln
        val timeFormat = SimpleDateFormat("HH:mm")
        val randomTime = currentTime.time
        return timeFormat.format(randomTime)
    }

    // Liste von Beispiel-Events
    var eventList: List<Event> = listOf(
        // Hier werden verschiedene Event-Objekte mit Beispieldaten erstellt
        Event(
            "Hamburger Liga",
            "FC KICKERS",
            6,
            "Advanced",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "St. Pauli Stadion",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
        Event(
            "Holstein Cup",
            "Friedländer CLT",
            3,
            "Improver",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "Kieler Promenade",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
        Event(
            "2024 Cup",
            "Dynamic Strickers",
            2,
            "Beginners",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "Sport-Vereins-Halle Berlin",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
        Event(
            "TennisMatch Gegner: Marius Fleb",
            "Friedländer CLT",
            null,
            null,
            null,
            generateRandomDate(30),
            generateRandomTime(),
            "Elmshorner SportPark",
            R.drawable.crash_test_m,
            null,
            null,
            null,
            true
        ),
        Event(
            "Wilsdorfer Tunier",
            "BAT WARRIORS",
            2,
            "Practitioners",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "Wilsdorfer Sportanlage",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
        Event(
            "RED BULL CUP 24",
            "SMASH MASTERS",
            4,
            "Expert",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "Berliner SV",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
        Event(
            "FRIENDS PLAY",
            "SPORT PARK BERLIN",
            4,
            "Beginner",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "Hamburger SportPark",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
        Event(
            "SMASH ROUND 2",
            "SMASH MASTERS",
            4,
            "Advanced",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "Halle Neuss",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
        Event(
            "RED BULL CUP",
            "SMASH MASTERS",
            4,
            "Expert",
            "11",
            generateRandomDate(30),
            generateRandomTime(),
            "Dingenfelder Anlage",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
    )

    // Funktion zum Laden der Events
    fun loadEvents(): List<Event> {
        return eventList
    }

    // Liste von Beispiel-News
    var newsList: List<News> = listOf(
        // Hier werden verschiedene News-Objekte mit Beispieldaten erstellt
        News(
            "Neue Sponsoren",
            "Zwei Sponsoren unterstützen die Veranstaltung, um sie auf ein neues Niveau zu heben."
        ),
        News(
            "Holstein Cup Ergebnisse",
            "Die Gewinner und Ergebnisse sind jetzt verfügbar. Herzlichen Glückwunsch an alle Teilnehmer."
        ),
        News(
            "Wichtige Änderung",
            "Der Austragungsort des Holstein Cups wurde geändert. Bitte beachten Sie die Aktualisierungen."
        ),
        News(
            "Rekordteilnahme",
            "Die diesjährige Ausgabe verzeichnet die höchste Teilnehmerzahl. Vielen Dank an die unterstützende Community."
        ),
        News(
            "Holstein Cup Champion",
            "Exklusives Interview mit dem diesjährigen Gewinner. Erfahren Sie mehr über seine Vorbereitungen und Gefühle."
        ),
        News(
            "Neue Jugendkategorie",
            "Die Veranstaltung erweitert das Teilnehmerfeld um eine Jugendkategorie zur Förderung junger Talente."
        ),
    )

    // Funktion zum Laden der News
    fun loadNews(): List<News> {
        return newsList
    }

    // Liste von Beispiel-Chats
    var chatList: List<Chat> = listOf(
        // Hier werden verschiedene Chat-Objekte mit Beispieldaten erstellt
        Chat(
            "GymBros",
            R.drawable.crash_test_m,
        ),
        Chat(
            "Holsteiner Eagels",
            R.drawable.crash_test_m,
        ),
        Chat(
            "Ski-Bergen 08",
            R.drawable.crash_test_m,
        ),
        Chat(
            "TurnGruppe",
            R.drawable.crash_test_m,
        ),
        Chat(
            "HH SV",
            R.drawable.crash_test_m,
        ),
        Chat(
            "SkatePark",
            R.drawable.crash_test_m,
        ),
        Chat(
            "Helo",
            R.drawable.crash_test_m,
        )
    )

    // Funktion zum Laden der Chats
    fun loadChats(): List<Chat> {
        return chatList
    }
}