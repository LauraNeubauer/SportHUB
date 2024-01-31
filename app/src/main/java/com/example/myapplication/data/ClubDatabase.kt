package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Club

class ClubDatabase {
    // LiveData-Objekt für die Clubdaten
    private val clubsData: MutableLiveData<List<Club>> = MutableLiveData()

    init {
        // Hier werden die Daten beim Erstellen der ClubDatabase initialisiert.
        clubsData.value = getClubData()
    }

    // Funktion, um LiveData-Objekt der Clubdaten abzurufen
    fun getClubs(): LiveData<List<Club>> {
        return clubsData
    }

    // Funktion zur Bereitstellung von Beispielclubdaten
    private fun getClubData(): MutableList<Club> {
        // Hier werden verschiedene Club-Objekte mit Beispieldaten erstellt
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
                "Seit 1985 stolz auf unser Fußballspiel. Gemeinsam kämpfen wir für Siege und Ehre. Die Tore sind unsere Geschichte, der Ball unser Herz.",
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
                "In jedem Schlag steckt unsere Dynamik. Seit 1992 vereinen wir Kraft und Präzision. 'Gemeinsam für den Sieg!' ist unser Credo auf dem Spielfeld.",
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
                "Seit 1978 zermalmen wir Ziele mit Fußballkraft. Tore sind unser Vermächtnis, Siege unser Ziel. 'Unsere Stärke liegt in jedem Schuss' - 75% Erfolgsquote.",
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
                "Seit 2000 fliegen unsere Federbälle. Schnell, geschickt, erfolgreich. 'Gemeinsam hoch hinaus!' ist unser Motto, 80% Quote spricht für sich.",
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
                "Badminton in Perfektion seit 1995. Schnell, präzise, unaufhaltsam. 'Schnelligkeit ist unser Schlüssel, Präzision unsere Waffe",
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
                "Seit 1988 mit Leidenschaft am Federball. 'Fliegende Bälle, brennende Herzen.' Mit 78% Erfolgsquote sind wir die Flammen.",
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
                "Seit 2005 agil und schnell auf dem Squash-Court. 'Eleganz und Siegeswille' sind unser Antrieb. 85% Treffgenauigkeit.",
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
                "Kraft und Technik im Squash-Erfolg. Seit 1998 dominieren wir den Court. Stärke in jedem Schlag, Technik im Triumph!",
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
                "Squash mit Präzision und Geschwindigkeit. Vereint im Erfolg seit 2002. 'Schnell, präzise, unschlagbar' - 88% Erfolgsquote.",
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
                "Tischtenniskünstler seit 1990. 'Unsere Bälle, unbezwingbar.' Mit 75% Treffgenauigkeit beherrschen wir den Tischtennisplatz.",
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
                "Schnell, präzise, Tischtennis-Meister seit 1995. 'Geschwindigkeit ist unsere Taktik, Präzision unser Erfolg.' 78% Treffgenauigkeit.",
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
                "Mit Topspin zum Tischtennis-Erfolg. Unser Team hat sich über die Jahre sehr gut entwickelt und wir sind seit Jahren die Liga-Meister",
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
                "Unsere drei Mannschaften spielen in verschiedenen Ligen, und unser Motto lautet: 'Gewinnen ist unser Ziel!' Wir organisieren auch jedes Jahr 20 Turniere.",
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
                "Auf dem Tennisplatz sind wir unschlagbar. Unsere Quote liegt bei 80 Prozent, danken wir unseren langjährigen Coaches Nick Brest, Uta Grin und Mario Unwald",
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
                "Mit Präzision und Kraft zum Sieg. Mit Morivation zum Platz. Mit guter Laune in das Spiel. Wir haben klare Regeln und Richtlinien, suchen derzeit neue Mitglieder!",
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
                "Eisige Präzision im Hockey seit 2008. 'Kälte, Präzision, Sieg.' Unsere Stärke, unser Puck, unsere Dominanz - 82% Erfolgsquote.",
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
                "Dominanz im Hockey seit 2015. 'Das Feld gehört uns.' Mit 78% Erfolgsquote brechen wir Regeln und erobern Herzen.",
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
                "Meisterhaft mit dem Puck seit 2002. 'Puck, Power, Perfektion.' Unsere Stärke, unser Spiel, unser Erfolg - 85% Treffgenauigkeit.",
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
                "Legenden des Cricketsports seit 1980. 'Jeder Schlag, ein Mythos.' Mit 88% Erfolgsquote prägen wir Geschichte.",
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
                "Krieger mit dem Schläger auf dem Cricketfeld seit 1992. 'Schlagkraft und Siegeswille.' 85% Erfolgsquote, unser Weg zum Ruhm.",
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
                "Die Zauberer des Wickets im Cricket seit 2000. 'Magie in jedem Wurf.' Mit 80% Erfolgsquote gestalten wir das Spiel der Legenden.",
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
                "Heldenhaft im Handball seit 1995. 'Jeder Wurf, ein Triumph.' Unsere Kraft, unser Spiel, unsere Siege - 78% Erfolgsquote.",
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
                "Torhüterhelden im Handball seit 2002. 'Tor um Tor, Sieg um Sieg.' Mit 80% Erfolgsquote beschützen wir unser Reich.",
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
                "Schnelle Breaks, Siege im Handball seit 1990. 'Tempo, Taktik, Triumph.' Unsere Spielweise, unsere Erfolge - 82% Erfolgsquote.",
                34
            )
        )
    }
}
