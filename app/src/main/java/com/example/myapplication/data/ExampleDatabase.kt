package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Chat
import com.example.myapplication.model.Event
import com.example.myapplication.model.ExamplePerson
import com.example.myapplication.model.News
import com.example.myapplication.model.Request
import com.example.myapplication.model.Results
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.random.Random


class ExampleDatabase {


    fun generateRandomDate(): String {
        // Aktuelles Datum erhalten
        val currentDate = Calendar.getInstance()

        // Zufällige Anzahl von Tagen hinzufügen (hier: bis zu 30 Tage)
        val randomDays = Random.nextInt(30)
        currentDate.add(Calendar.DAY_OF_YEAR, randomDays)

        // Datum in das gewünschte Format umwandeln
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val randomDate = currentDate.time
        return dateFormat.format(randomDate)
    }

    fun generateRandomTime(): String {
        // Aktuelle Uhrzeit erhalten
        val currentTime = Calendar.getInstance()

        // Zufällige Stunde und Minute hinzufügen
        val randomHour = Random.nextInt(24)
        val randomMinute = Random.nextInt(60)
        currentTime.set(Calendar.HOUR_OF_DAY, randomHour)
        currentTime.set(Calendar.MINUTE, randomMinute)

        // Uhrzeit in das gewünschte Format umwandeln
        val timeFormat = SimpleDateFormat("HH:mm")
        val randomTime = currentTime.time
        return timeFormat.format(randomTime)
    }

    var eventList: List<Event> = listOf(
        Event(
            "Hamburger Liga",
            "FC KICKERS",
            6,
            "Advanced",
            "11",
            generateRandomDate(),
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
            generateRandomDate(),
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
            generateRandomDate(),
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
            generateRandomDate(),
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
            generateRandomDate(),
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
            generateRandomDate(),
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
            generateRandomDate(),
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
            generateRandomDate(),
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
            generateRandomDate(),
            generateRandomTime(),
            "Dingenfelder Anlage",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash,
            false
        ),
    )

    fun loadEvents(): List<Event> {
        return eventList
    }

    var newsList: List<News> = listOf(
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


    fun loadNews(): List<News> {
        return newsList
    }

    var examplePersonLists: List<ExamplePerson> = listOf(
        ExamplePerson("Anna", "Friedel", "22", "Beginner", "23", 12, 18, "Hockeyclub Hamburg"),
        ExamplePerson("Max", "Mustermann", "30", "Intermediate", "42", 10, 15, "Football Club"),
        ExamplePerson("Lena", "Schmidt", "25", "Advanced", "55", 8, 20, "Tennis Club"),
        ExamplePerson("Jan", "Müller", "28", "Beginner", "37", 14, 16, "Chess Club"),
        ExamplePerson("Sophie", "Schneider", "33", "Advanced", "61", 9, 22, "Swimming Club"),
    )

    fun loadPersons(): List<ExamplePerson> {
        return examplePersonLists
    }

    var matchList: List<Request> = listOf(
        Request(examplePersonLists[0], false),
        Request(examplePersonLists[1], false),
        Request(examplePersonLists[2], false),
        Request(examplePersonLists[3], false),
        Request(examplePersonLists[4], false),
    )

    fun loadMatches(): List<Request> {
        return matchList
    }

    var chatList: List<Chat> = listOf(
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

    fun loadChats(): List<Chat> {
        return chatList
    }


    val ResultList: List<Results> = listOf(
        Results(
            "HEUTE, 19:50 Uhr",
            "FUSSBALL-FREUNDSCHAFTSSPIEL",
            "StadionSignal Iduna Park, Dortmund",
            "Borussia Dortmund",
            "Gruppe F",
            "Paris Saint-Germain",
            "Gruppe F",
            "0:0"
        ),
        Results(
            "GESTERN, 18:20 Uhr",
            "Champions League",
            "StadionMarakana, Belgrad",
            "Roter Stern Belgra",
            "Gruppe G",
            "Manchester City",
            "Gruppe G",
            "0:1"
        ),
        Results(
            "GESTERN, 15:30 Uhr",
            "Bundesliga",
            "Allianz Arena, München",
            "FC Bayern München",
            "Gruppe A",
            "Borussia Mönchengladbach",
            "Gruppe A",
            "2:1"
        ),
        Results(
            "MORGEN, 20:00 Uhr",
            "Premier League",
            "Old Trafford, Manchester",
            "Manchester United",
            "Gruppe B",
            "Liverpool FC",
            "Gruppe B",
            "0:0"
        ),
        Results(
            "MORGEN, 21:00 Uhr",
            "La Liga",
            "Camp Nou, Barcelona",
            "FC Barcelona",
            "Gruppe C",
            "Real Madrid",
            "Gruppe C",
            "0:0"
        ),
        Results(
            "ÜBERMORGEN, 19:45 Uhr",
            "Serie A",
            "San Siro, Mailand",
            "AC Mailand",
            "Gruppe D",
            "Juventus",
            "Gruppe D",
            "0:0"
        ),
        Results(
            "05.03.2023, 18:30 Uhr",
            "Eredivisie",
            "Johan Cruyff Arena, Amsterdam",
            "Ajax Amsterdam",
            "Gruppe E",
            "PSV Eindhoven",
            "Gruppe E",
            "0:0"
        ),
        Results(
            "07.03.2023, 20:15 Uhr",
            "Primeira Liga",
            "Estádio da Luz, Lissabon",
            "Benfica Lissabon",
            "Gruppe F",
            "FC Porto",
            "Gruppe F",
            "0:0"
        ),
        Results(
            "10.03.2023, 17:00 Uhr",
            "Super Lig",
            "Türk Telekom Stadyumu, Istanbul",
            "Galatasaray Istanbul",
            "Gruppe G",
            "Fenerbahçe",
            "Gruppe G",
            "0:0"
        ),
        Results(
            "12.03.2023, 16:45 Uhr",
            "Russian Premier League",
            "Gazprom Arena, Sankt Petersburg",
            "Zenit Sankt Petersburg",
            "Gruppe H",
            "CSKA Moskau",
            "Gruppe H",
            "0:0"
        )
    )

    fun loadResults(): List<Results> {
        return ResultList
    }
}