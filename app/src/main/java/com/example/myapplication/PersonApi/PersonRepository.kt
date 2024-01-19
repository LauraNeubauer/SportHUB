package com.example.myapplication.PersonApi

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.PersonApi.local.PersonDatabase
import com.example.myapplication.PersonApi.model.Person
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.PersonApi.remote.PersonApi
import java.util.Calendar


class PersonRepository(
    private val db: PersonDatabase
) {
    val personenListe = db.personDao.getAll()

    fun getPeopleInChat(chatId: String): LiveData<List<PersonData>> {
        return db.personDao.getPeopleInChat(chatId, 30)
    }

    suspend fun getPerson() {
        val numberOfPersons = 100
        val personList = mutableListOf<Person>()

        repeat(numberOfPersons) {
            val response = PersonApi.retrofitService.getPerson()
            personList.addAll(response.results)

            val person = response.results.first()

            val winPercentage = (10..60).random()
            val club = (1..21).random()

            val currentDate = Calendar.getInstance().time
            val calendar = Calendar.getInstance()
            calendar.time = currentDate
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)

            fun date() : String {
                var newDay = (dayOfMonth + club).toString()
                var newMonth = month
                if (newDay.toInt() > 31) {
                    newMonth + 1
                }
                var newDate = newDay.toString() + "." + newMonth + ".2024"
                return newDate
            }


            val sportsOne = listOf<String>(
                "BADMINTON",
                "SQUASH",
                "TISCHTENNIS",
                "TENNIS",
                "FUSSBALL",
                "HOCKEY",
                "CRICKET",
                "HANDBALL"
            )
            val sportsTwo = listOf<String>(
                "BADMINTON",
                "SQUASH",
                "TISCHTENNIS",
                "TENNIS",
                "FUSSBALL",
                "HOCKEY",
                "CRICKET",
                "HANDBALL"
            )
            val sportOne = sportsOne.random()
            val sportTwo = if (sportOne == "BADMINTON") {
                listOf<String>(
                    "RUGBY",
                    "GOLF",
                    "CRICKET"
                ).random()
            } else sportsTwo.random()
            val personFullName = "${person.name.first} ${person.name.last}"
            val personAge = person.dob.age
            val personPicture = person.picture.large
            val personMatches = person.location.street.number
            val personTrophys = person.registered.age
            val personWins =
                ((winPercentage / 100.0) * personMatches).coerceIn(1.0, personMatches.toDouble())
                    .toInt()
            val personSize =
                if (person.gender == "female") {
                    (156..176).random()
                } else {
                    (168..210).random()
                }
            val personLevel =
                when (personWins) {
                    in (1..20) -> {
                        "BEGINNER"
                    }

                    in (20..80) -> {
                        "IMPROVER"
                    }

                    in (80..150) -> {
                        "ADVANCED"
                    }

                    in (150..200) -> {
                        "EXPERT"
                    }

                    else -> {
                        "ELITE"
                    }
                }

            if (!isArabicName(personFullName) &&
                personFullName.length <= 18 &&
                personAge in 16..65 &&
                personTrophys in 1..999 &&
                personTrophys < personWins &&
                personMatches in 1..999
            ) {
                val personData = PersonData(
                    name = personFullName,
                    gender = person.gender,
                    age = personAge.toString(),
                    pic = personPicture,
                    trophys = personTrophys.toString(),
                    matches = personMatches.toString(),
                    wins = personWins.toString(),
                    size = personSize.toString(),
                    level = personLevel,
                    sportsOne = sportOne,
                    sportsTwo = sportTwo,
                    bio = randomBio(),
                    club = club,
                    chat = getGroupChat(),
                    entfernung = entfernung(),
                    date = date()
                )
                db.personDao.insertPerson(personData)
                Log.d("TAG", "Person in die Liste geladen")
            }
        }
    }

    suspend fun insertPerson(person: PersonData) {
        try {
            db.personDao.insertPerson(person)
        } catch (e: Exception) {
            Log.d("TAG", "Neue Person in die Liste geladen")
        }
    }

    private fun isArabicName(name: String): Boolean {
        val arabicRegex = Regex("[\\p{InArabic}]+")
        return arabicRegex.containsMatchIn(name)
    }
    private fun getGroupChat(): String {
        val group = listOf(
            "FitFam Legends",
            "Muscle Mavericks",
            "Sweat Sesh Squad",
            "Iron Tribe",
            "Flex Fusion Crew",
            "Powerhouse Pioneers",
            "Adrenaline Allies",
            "Ripped Rebels",
            "Beast Mode Brigade",
            "Epic Elevate Collective",
            "Warrior Workout Warriors",
            "Grit Gang",
            "Zen & Zeal Fitness Crew",
            "Sculpted Syndicate",
            "Pump Posse"
        )
        return group.random()
    }

    private fun entfernung(): Int {
        val entfernung: List<Int> = listOf(
            1, 2, 3, 5, 7, 8, 11, 13, 15, 17, 19, 22, 25, 28, 33, 43, 45, 55, 56, 67, 80, 90)
        return entfernung.random()
    }
    private fun randomBio(): String {
        val randomBiography = listOf(
            "Begeisterter Tennisspieler, Kletterer, Schwimmer und Golfenthusiast. Immer auf der Suche nach neuen sportlichen Herausforderungen und Adrenalinkicks.",
            "Leidenschaftlicher Basketballer, Radsportler, Volleyballfanatiker und passionierter Läufer. Der Sport ist meine Leidenschaft, und ich genieße jede Minute auf dem Spielfeld oder der Strecke.",
            "Liebhaber von Tischtennis, Windsurfen, Badminton und Skateboarden. Mein Leben dreht sich um den Nervenkitzel des Sports, egal ob auf dem Wasser, auf dem Platz oder auf der Straße.",
            "Hockeybegeisterter, Skilangläufer, Rugbyfan und begeisterter Leichtathlet. Sport ist nicht nur meine Leidenschaft, sondern auch meine geliebte Lebensphilosophie.",
            "Fußballspieler, Snowboarder, Kletterenthusiast und passionierter Bogenschütze. Immer auf der Suche nach neuen Möglichkeiten, meine sportlichen Fähigkeiten zu verbessern.",
            "Liebhaber von Beachvolleyball, Skifahren, Radfahren und Ultimate Frisbee. Die Vielfalt des Sports fasziniert mich, und ich liebe es, mich in verschiedenen Disziplinen zu beweisen.",
            "Baseballfanatiker, Surfer, Squashspieler und Hürdenläufer. Der Sport gibt meinem Leben den nötigen Schwung, und ich bin stets bereit für neue sportliche Abenteuer mit anderen.",
            "Tischfußballprofi, Skater, Handballspieler und leidenschaftlicher Trailrunner. Sportliche Vielfalt ist für mich der Schlüssel zu einem erfüllten, langem und vorallem gesunden Leben.",
            "Volleyballspieler, Skispringer, Tennisschläger-Schwingender und Marathonläufer. Der Sport ist meine Leidenschaft, und ich liebe die Herausforderung, meine Grenzen zu überschreiten.",
            "Basketballenthusiast, Wakeboarder, Golfspieler und Marathonläufer. Sport ist für mich nicht nur körperliche Betätigung, sondern auch eine Quelle der Freude und des Teamgeists.",
            "Ein Abenteurer auf und neben dem Spielfeld: Wenn ich nicht gerade meine Leidenschaft für den Hockey-Stock auslebe, finde mich beim Snowboarden oder Dartwerfen",
            "In der Welt des Sports bin ich zuhause, sei es beim Jonglieren mit Fußbällen, dem Ritt auf der Snowboardwelle, dem Dartspiel oder dem eleganten Tanz auf dem Hockeyfeld.",
            "Ein Spieler, egal ob auf dem grünen Rasen, in der eisigen Hockeyarena, auf dem Dartboard oder den verschneiten Pisten. Sport ist Ausdruck meiner Leidenschaft und meines individuellen Stils.",
            "Mein Herz schlägt im Takt des Sports, sei es beim Dribbeln auf dem Fußballfeld, dem Dartpfeile-Werfen, dem eleganten Gleiten auf dem Snowboard oder einem Duell auf dem Hockeyplatz.",
            "Ein Künstler auf dem Spielfeld und abseits davon: ob im Dartspiel, auf dem Hockeyfeld, beim Fußball oder auf dem Snowboard. Meine Sportarten sind meine bunten Leinwände.",
            "Mein Leben ist ein Kaleidoskop des Sports, vom Dartwerfen bis zur Dynamik auf dem Fußballfeld, von der Geschwindigkeit beim Snowboarden bis zur Raffinesse auf dem Hockeyplatz.",
            "Ein Spieler, sei es mit dem Fußball, dem Dartpfeil, dem Snowboard oder dem Hockey-Schläger. Mein Spiel ist meine Kunst, und die Vielfalt der Sportarten gibt meinem Leben Farbe und Energie.",
            "Ich jongliere nicht nur mit Bällen auf dem Fußballfeld, sondern auch mit Dartpfeilen, snowboarde durch das Leben und tanze auf dem glitzerndem Eis des Hockeyplatzes.",
            "Ob auf dem Spielfeld oder vor dem Dartboard oder mit dem Fußball am Fuß – in jedem dieser Momente offenbart sich meine Liebe zum Sport und meine Bereitschaft, neue Herausforderungen anzunehmen.",
            "Meine Welt ist ein Spiel, sei es auf dem Fußballplatz, auf dem Dartboard, auf dem Snowboard oder auf dem Hockeyfeld. Jede Sportart ist für mich eine einzigartige Symphonie.",
            "Die Bühne meines Lebens ist vielfältig: sei es der glatte Eisring des Hockeyfelds, das präzise Dartboard, der grüne Rasen des Fußballplatzes oder die verschneite Piste beim Snowboarden.",
            "Mein Lebensweg ist ein Parcours aus unterschiedlichen Sportarten – von der Präzision des Dartwerfens über das temporeiche Snowboarden bis hin zur Teamdynamik auf dem Fußballfeld.",
            "Ein leidenschaftlicher Freigeist im Sport: sei es auf dem Fußballfeld, in der Dart-Arena, auf der verschneiten Piste des Snowboards oder dem eisigen Hockeyplatz.",
            "Im Rhythmus der Sportvielfalt tanze ich durch mein Leben: sei es auf dem Dartboard, dem Fußballfeld, der schneebedeckten Piste beim Snowboarden oder dem Hockeyplatz.",
            "Meine Welt ist ein Mosaik aus Sportarten: sei es der Anstoß auf dem Fußballfeld, der gezielte Dartwurf, das elegante Gleiten beim Snowboarden oder das Duell auf dem Hockeyplatz."
        )
        return randomBiography.random()
    }
}
