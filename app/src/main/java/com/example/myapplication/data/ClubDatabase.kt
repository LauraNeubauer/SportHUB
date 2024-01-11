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
                "Fußball",
                250,
                1985,
                23,
                4,
                "68%",
                40,
                "Wir sind stolz darauf, seit 1985 Fußball zu spielen."
            ),
            Club(
                "Dynamic Strikers",
                "Fußball",
                180,
                1992,
                15,
                3,
                "72%",
                30,
                "Gemeinsam für den Sieg!"
            ),
            Club(
                "Goal Crushers",
                "Fußball",
                300,
                1978,
                30,
                5,
                "75%",
                35,
                "Unsere Tore sind unsere Stärke."
            ),
            Club(
                "Shuttle Masters",
                "Badminton",
                120,
                2000,
                10,
                2,
                "80%",
                25,
                "Fliegende Federbälle, geschickte Spieler."
            ),
            Club(
                "Swift Smashers",
                "Badminton",
                90,
                1995,
                8,
                1,
                "76%",
                20,
                "Schnell und präzise auf dem Badmintonplatz."
            ),
            Club(
                "Birdie Blazers",
                "Badminton",
                150,
                1988,
                12,
                3,
                "78%",
                22,
                "Mit Leidenschaft für das Federballspiel."
            ),
            Club(
                "Squash Stars",
                "Squash",
                80,
                2005,
                5,
                2,
                "85%",
                15,
                "Schnell und agil auf dem Squash-Court."
            ),
            Club(
                "Powerful Rackets",
                "Squash",
                100,
                1998,
                8,
                3,
                "82%",
                18,
                "Mit Kraft und Technik zum Squash-Erfolg."
            ),
            Club(
                "Smash Masters",
                "Squash",
                120,
                2002,
                7,
                2,
                "88%",
                20,
                "Präzision und Geschwindigkeit vereint im Squash."
            ),
            Club(
                "Spin Kings",
                "Tischtennis",
                150,
                1990,
                12,
                3,
                "75%",
                25,
                "Unsere Tischtenniskünste sind unschlagbar."
            ),
            Club(
                "Fast Paddlers",
                "Tischtennis",
                120,
                1995,
                10,
                2,
                "78%",
                20,
                "Schnell und präzise am Tischtennistisch."
            ),
            Club(
                "Top Spinners",
                "Tischtennis",
                180,
                1988,
                15,
                4,
                "80%",
                30,
                "Mit Topspin zum Tischtennis-Erfolg."
            ),
            Club(
                "Tennisverein XYZ",
                "Tennis",
                183,
                1990,
                17,
                3,
                "74%",
                34,
                "Unsere drei Mannschaften spielen in verschiedenen Ligen, und unser Motto lautet: 'Gewinnen ist unser Ziel!' Wir organisieren auch jedes Jahr 20 Turniere, um die Begeisterung für Tennis in unserer Gemeinschaft zu fördern."
            ),
            Club(
                "Ace Aces",
                "Tennis",
                200,
                1982,
                25,
                4,
                "80%",
                30,
                "Auf dem Tennisplatz sind wir unschlagbar."
            ),
            Club(
                "Net Ninjas",
                "Tennis",
                150,
                1995,
                20,
                3,
                "72%",
                25,
                "Mit Präzision und Kraft zum Sieg."
            ),
            Club(
                "Icebreakers",
                "Hockey",
                120,
                2008,
                8,
                2,
                "82%",
                18,
                "Eisige Präzision auf dem Hockeyfeld."
            ),
            Club(
                "Field Dominators",
                "Hockey",
                90,
                2015,
                5,
                1,
                "78%",
                15,
                "Dominanz im Hockeyfeld."
            ),
            Club(
                "Puck Masters",
                "Hockey",
                150,
                2002,
                10,
                3,
                "85%",
                20,
                "Meisterhaft mit dem Puck auf dem Hockeyfeld."
            ),
            Club(
                "Cricket Legends",
                "Cricket",
                200,
                1980,
                20,
                5,
                "88%",
                25,
                "Legenden des Cricketsports."
            ),
            Club(
                "Bat Warriors",
                "Cricket",
                180,
                1992,
                18,
                4,
                "85%",
                30,
                "Krieger mit dem Schläger auf dem Cricketfeld."
            ),
            Club(
                "Wicket Wizards",
                "Cricket",
                150,
                2000,
                15,
                3,
                "80%",
                22,
                "Die Zauberer des Wickets im Cricket."
            ),
            Club(
                "Handball Heroes",
                "Handball",
                160,
                1995,
                12,
                3,
                "78%",
                22,
                "Heldenhaft im Handball."
            ),
            Club(
                "Goal Guardians",
                "Handball",
                140,
                2002,
                10,
                2,
                "80%",
                20,
                "Torhüterhelden im Handball."
            ),
            Club(
                "Fast Breakers",
                "Handball",
                180,
                1990,
                15,
                4,
                "82%",
                25,
                "Schnelle Breaks und Siege im Handball."
            )
        )
    }
}
//Fussball, badminton, Squash, tischtennis, tennis, hockey, cricket, handball
