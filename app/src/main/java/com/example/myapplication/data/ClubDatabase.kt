package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Club

class ClubDatabase {
    private val clubsData: MutableLiveData<List<Club>> = MutableLiveData()

    init {
        // Hier werden die Daten beim Erstellen der ClubDatabase initialisiert.
        clubsData.value = getClubData()
    }

    fun getClubs(): LiveData<List<Club>> {
        return clubsData
    }

    private fun getClubData(): MutableList<Club> {
        return mutableListOf<Club>(
            Club(
                "FC Kickers",
                "FUSSBALL",
                250,
                1985,
                23,
                4,
                "68%",
                40,
                "Wir sind stolz darauf, seit 1985 Fußball zu spielen.",
                3
            ),
            Club(
                "Dynamic Strikers",
                "FUSSBALL",
                180,
                1992,
                15,
                3,
                "72%",
                30,
                "Gemeinsam für den Sieg!",
                12
            ),
            Club(
                "Goal Crushers",
                "FUSSBALL",
                300,
                1978,
                30,
                5,
                "75%",
                35,
                "Unsere Tore sind unsere Stärke.",
                11
            ),
            Club(
                "Shuttle Masters",
                "BADMINTON",
                120,
                2000,
                10,
                2,
                "80%",
                25,
                "Fliegende Federbälle, geschickte Spieler.",
                15
            ),
            Club(
                "Swift Smashers",
                "BADMINTON",
                90,
                1995,
                8,
                1,
                "76%",
                20,
                "Schnell und präzise auf dem Badmintonplatz.",
                22
            ),
            Club(
                "Birdie Blazers",
                "BADMINTON",
                150,
                1988,
                12,
                3,
                "78%",
                22,
                "Mit Leidenschaft für das Federballspiel.",
                45
            ),
            Club(
                "Squash Stars",
                "SQUASH",
                80,
                2005,
                5,
                2,
                "85%",
                15,
                "Schnell und agil auf dem Squash-Court.",
                55
            ),
            Club(
                "Powerful Rackets",
                "SQUASH",
                100,
                1998,
                8,
                3,
                "82%",
                18,
                "Mit Kraft und Technik zum Squash-Erfolg.",
                34
            ),
            Club(
                "Smash Masters",
                "SQUASH",
                120,
                2002,
                7,
                2,
                "88%",
                20,
                "Präzision und Geschwindigkeit vereint im Squash.",
                70
            ),
            Club(
                "Spin Kings",
                "TISCHTENNIS",
                150,
                1990,
                12,
                3,
                "75%",
                25,
                "Unsere Tischtenniskünste sind unschlagbar.",
                60
            ),
            Club(
                "Fast Paddlers",
                "TISCHTENNIS",
                120,
                1995,
                10,
                2,
                "78%",
                20,
                "Schnell und präzise am Tischtennistisch.",
                34
            ),
            Club(
                "Top Spinners",
                "TISCHTENNIS",
                180,
                1988,
                15,
                4,
                "80%",
                30,
                "Mit Topspin zum Tischtennis-Erfolg.",
                90
            ),
            Club(
                "Tennisverein XYZ",
                "TENNIS",
                183,
                1990,
                17,
                3,
                "74%",
                34,
                "Unsere drei Mannschaften spielen in verschiedenen Ligen, und unser Motto lautet: 'Gewinnen ist unser Ziel!' Wir organisieren auch jedes Jahr 20 Turniere, um die Begeisterung für Tennis in unserer Gemeinschaft zu fördern.",
                34
            ),
            Club(
                "Ace Aces",
                "TENNIS",
                200,
                1982,
                25,
                4,
                "80%",
                30,
                "Auf dem Tennisplatz sind wir unschlagbar.",
                60
            ),
            Club(
                "Net Ninjas",
                "TENNIS",
                150,
                1995,
                20,
                3,
                "72%",
                25,
                "Mit Präzision und Kraft zum Sieg.",
                35
            ),
            Club(
                "Icebreakers",
                "HOCKEY",
                120,
                2008,
                8,
                2,
                "82%",
                18,
                "Eisige Präzision auf dem Hockeyfeld.",
                44
            ),
            Club(
                "Field Dominators",
                "HOCKEY",
                90,
                2015,
                5,
                1,
                "78%",
                15,
                "Dominanz im Hockeyfeld.",
                67
            ),
            Club(
                "Puck Masters",
                "HOCKEY",
                150,
                2002,
                10,
                3,
                "85%",
                20,
                "Meisterhaft mit dem Puck auf dem Hockeyfeld.",
                22
            ),
            Club(
                "Cricket Legends",
                "CRICKET",
                200,
                1980,
                20,
                5,
                "88%",
                25,
                "Legenden des Cricketsports.",
                3
            ),
            Club(
                "Bat Warriors",
                "CRICKET",
                180,
                1992,
                18,
                4,
                "85%",
                30,
                "Krieger mit dem Schläger auf dem Cricketfeld.",
                17
            ),
            Club(
                "Wicket Wizards",
                "CRICKET",
                150,
                2000,
                15,
                3,
                "80%",
                22,
                "Die Zauberer des Wickets im Cricket.",
                22
            ),
            Club(
                "Handball Heroes",
                "HANDBALL",
                160,
                1995,
                12,
                3,
                "78%",
                22,
                "Heldenhaft im Handball.",
                1
            ),
            Club(
                "Goal Guardians",
                "HANDBALL",
                140,
                2002,
                10,
                2,
                "80%",
                20,
                "Torhüterhelden im Handball.",
                14
            ),
            Club(
                "Fast Breakers",
                "HANDBALL",
                180,
                1990,
                15,
                4,
                "82%",
                25,
                "Schnelle Breaks und Siege im Handball.",
                34
            )
        )
    }
}
