package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Chat
import com.example.myapplication.model.Club
import com.example.myapplication.model.Event
import com.example.myapplication.model.News
import com.example.myapplication.model.Person
import com.example.myapplication.model.Request
import com.example.myapplication.model.Results

class ExampleDatabase {

    var eventList: List<Event> = listOf(
        Event(
            "Holstein Cup",
            "Friedländer CLT",
            6,
            "fortgeschrittene",
            "11",
            "20.01.2024, 13:20 Uhr",
            "Elmshorner SportPark",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash
        ),
        Event(
            "Holstein Cup",
            "Friedländer CLT",
            3,
            "fortgeschrittene",
            "11",
            "20.01.2024, 13:20 Uhr",
            "Elmshorner SportPark",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash
        ),
        Event(
            "Holstein Cup",
            "Friedländer CLT",
            2,
            "fortgeschrittene",
            "11",
            "20.01.2024, 13:20 Uhr",
            "Elmshorner SportPark",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash
        ),
        Event(
            "Holstein Cup",
            "Friedländer CLT",
            3,
            "fortgeschrittene",
            "11",
            "20.01.2024, 13:20 Uhr",
            "Elmshorner SportPark",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash
        ),
        Event(
            "Holstein Cup",
            "Friedländer CLT",
            2,
            "fortgeschrittene",
            "11",
            "20.01.2024, 13:20 Uhr",
            "Elmshorner SportPark",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash
        ),
        Event(
            "Holstein Cup",
            "Friedländer CLT",
            4,
            "fortgeschrittene",
            "11",
            "20.01.2024, 13:20 Uhr",
            "Elmshorner SportPark",
            R.drawable.crash_test_m,
            R.drawable.edgar_chaparro_shfo3woggtu_unsplash,
            R.drawable.jack_van_tricht_izwepu8aqbc_unsplash,
            R.drawable.karsten_winegeart_r6zarepldje_unsplash
        ),
    )

    fun loadEvents(): List<Event> {
        return eventList
    }

    var newsList: List<News> = listOf(
        News(
            "Holstein Cup: Neue Sponsoren gewonnen",
            "Zwei Sponsoren unterstützen die Veranstaltung, um sie auf ein neues Niveau zu heben."
        ),
        News(
            "Holstein Cup Ergebnisse veröffentlicht",
            "Die Gewinner und Ergebnisse sind jetzt verfügbar. Herzlichen Glückwunsch an alle Teilnehmer."
        ),
        News(
            "Wichtige Änderung am Veranstaltungsort",
            "Der Austragungsort des Holstein Cups wurde geändert. Bitte beachten Sie die Aktualisierungen."
        ),
        News(
            "Holstein Cup: Rekordteilnahme",
            "Die diesjährige Ausgabe verzeichnet die höchste Teilnehmerzahl. Vielen Dank an die unterstützende Community."
        ),
        News(
            "Interview mit Holstein Cup Champion",
            "Exklusives Interview mit dem diesjährigen Gewinner. Erfahren Sie mehr über seine Vorbereitungen und Gefühle."
        ),
        News(
            "Neue Jugendkategorie beim Holstein Cup",
            "Die Veranstaltung erweitert das Teilnehmerfeld um eine Jugendkategorie zur Förderung junger Talente."
        ),
    )


    fun loadNews(): List<News> {
        return newsList
    }

    var personList: List<Person> = listOf(
        Person("Anna", "Friedel", "22", "Beginner", "23", 12, 18, "Hockeyclub Hamburg"),
        Person("Max", "Mustermann", "30", "Intermediate", "42", 10, 15, "Football Club"),
        Person("Lena", "Schmidt", "25", "Advanced", "55", 8, 20, "Tennis Club"),
        Person("Jan", "Müller", "28", "Beginner", "37", 14, 16, "Chess Club"),
        Person("Sophie", "Schneider", "33", "Advanced", "61", 9, 22, "Swimming Club"),
    )

    fun loadPersons(): List<Person> {
        return personList
    }

    var matchList: List<Request> = listOf(
        Request(personList[0], false),
        Request(personList[1], false),
        Request(personList[2], false),
        Request(personList[3], false),
        Request(personList[4], false),
    )

    fun loadMatches(): List<Request> {
        return matchList
    }

    var chatList: List<Chat> = listOf(
        Chat(
            "GymBros",
            "Fritz",
            "Gestern habe ich vergessen Tschüß zu sagen",
            "Gestern",
            R.drawable.crash_test_m
        ),
        Chat(
            "Holsteiner Eagels",
            "Torben",
            "Nicht vergessen: Am Mittwoch kein...",
            "Heute",
            R.drawable.crash_test_m
        ),
        Chat(
            "Ski-Bergen 08",
            "Tanja",
            "Hey Ihr Lieben, ich habe am...",
            "Mittwoch",
            R.drawable.crash_test_m
        ),
        Chat(
            "GymBros",
            "Fritz",
            "Gestern habe ich vergessen Tschüß zu sagen",
            "Gestern",
            R.drawable.crash_test_m
        ),
        Chat(
            "Holsteiner Eagels",
            "Torben",
            "Nicht vergessen: Am Mittwoch kein...",
            "Heute",
            R.drawable.crash_test_m
        ),
        Chat(
            "Ski-Bergen 08",
            "Tanja",
            "Hey Ihr Lieben, ich habe am...",
            "Mittwoch",
            R.drawable.crash_test_m
        )
    )

    fun loadChats() : List<Chat> {
        return chatList
    }

    var ClubList : List<Club> = listOf(
        Club("Hamburger-Sportverein", "Hamburg, Deutschland", "1840", 293, 3200, "Dies ist die Bio über den HSV. Hier gibt es viel geschichte zu den alten zeiten, als der HSV noch in Kinderschuhen gesteckt hat."),
        Club("Hamburger-Sportverein", "Hamburg, Deutschland", "1840", 293, 3200, "Dies ist die Bio über den HSV. Hier gibt es viel geschichte zu den alten zeiten, als der HSV noch in Kinderschuhen gesteckt hat."),
        Club("HockeyClub 22", "Hamburg, Deutschland", "1920", 342, 323, "Dies ist die Bio über den HSV. Hier gibt es viel geschichte zu den alten zeiten, als der HSV noch in Kinderschuhen gesteckt hat."),
        Club("HockeyClub 22", "Hamburg, Deutschland", "1920", 342, 323, "Dies ist die Bio über den HSV. Hier gibt es viel geschichte zu den alten zeiten, als der HSV noch in Kinderschuhen gesteckt hat."),
        Club("HockeyClub 22", "Hamburg, Deutschland", "1920", 342, 323, "Dies ist die Bio über den HSV. Hier gibt es viel geschichte zu den alten zeiten, als der HSV noch in Kinderschuhen gesteckt hat.")
    )

    fun loadClubs() : List<Club> {
        return ClubList
    }

    val ResultList: List<Results> = listOf(
        Results("HEUTE, 19:50 Uhr", "FUSSBALL-FREUNDSCHAFTSSPIEL", "StadionSignal Iduna Park, Dortmund", "Borussia Dortmund", "Gruppe F", "Paris Saint-Germain", "Gruppe F", "0:0"),
        Results("GESTERN, 18:20 Uhr", "Champions League", "StadionMarakana, Belgrad", "Roter Stern Belgra", "Gruppe G", "Manchester City", "Gruppe G", "0:1"),
        Results("GESTERN, 15:30 Uhr", "Bundesliga", "Allianz Arena, München", "FC Bayern München", "Gruppe A", "Borussia Mönchengladbach", "Gruppe A", "2:1"),
        Results("MORGEN, 20:00 Uhr", "Premier League", "Old Trafford, Manchester", "Manchester United", "Gruppe B", "Liverpool FC", "Gruppe B", "0:0"),
        Results("MORGEN, 21:00 Uhr", "La Liga", "Camp Nou, Barcelona", "FC Barcelona", "Gruppe C", "Real Madrid", "Gruppe C", "0:0"),
        Results("ÜBERMORGEN, 19:45 Uhr", "Serie A", "San Siro, Mailand", "AC Mailand", "Gruppe D", "Juventus", "Gruppe D", "0:0"),
        Results("05.03.2023, 18:30 Uhr", "Eredivisie", "Johan Cruyff Arena, Amsterdam", "Ajax Amsterdam", "Gruppe E", "PSV Eindhoven", "Gruppe E", "0:0"),
        Results("07.03.2023, 20:15 Uhr", "Primeira Liga", "Estádio da Luz, Lissabon", "Benfica Lissabon", "Gruppe F", "FC Porto", "Gruppe F", "0:0"),
        Results("10.03.2023, 17:00 Uhr", "Super Lig", "Türk Telekom Stadyumu, Istanbul", "Galatasaray Istanbul", "Gruppe G", "Fenerbahçe", "Gruppe G", "0:0"),
        Results("12.03.2023, 16:45 Uhr", "Russian Premier League", "Gazprom Arena, Sankt Petersburg", "Zenit Sankt Petersburg", "Gruppe H", "CSKA Moskau", "Gruppe H", "0:0")
    )

    fun loadResults() : List<Results> {
        return ResultList
    }
}