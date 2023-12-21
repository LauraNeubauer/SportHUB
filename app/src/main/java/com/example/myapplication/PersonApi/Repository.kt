package com.example.myapplication.PersonApi

import android.util.Log
import com.example.myapplication.PersonApi.local.PersonDatabase
import com.example.myapplication.PersonApi.model.Person
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.PersonApi.remote.PersonApi


class Repository(
    private val db: PersonDatabase
) {
    val personenListe = db.personDao.getAll()

    suspend fun getGroup(participants: Int) {
        db.personDao.getGroup(participants)
    }

    suspend fun getPerson() {
        val numberOfPersons = 100
        val personList = mutableListOf<Person>()

        repeat(numberOfPersons) {
            val response = PersonApi.retrofitService.getPerson()
            personList.addAll(response.results)

            val person = response.results.first()

            val winPercentage = (10..60).random()

            val sportsOne = listOf<String>(
                "HOCKEY",
                "TENNIS",
                "BADMINTON",
                "SQUASH",
            )
            val sportsTwo = listOf<String>(
                "TISCHTENNIS",
                "FUSSBALL",
                "RUGBY",
                "GOLF",
                "CRICKET",
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
                        "PRACTITIONER"
                    }

                    else -> {
                        "EXPERT"
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
                    message = randomMessage(),
                    club = getClub(),
                    chat = getGroupChat(),
                    chattwo = getGroupChat(),
                    messagewritten = getTime(),
                    messagetwo = randomMessage(),
                    messagetwowritten = getTime()
                )
                db.personDao.insertPerson(personData)
                Log.d("TAG", "Person in die Liste geladen")
            }
        }
    }

    private fun isArabicName(name: String): Boolean {
        val arabicRegex = Regex("[\\p{InArabic}]+")
        return arabicRegex.containsMatchIn(name)
    }

    private fun getClub(): String {
        val randomClub = listOf("Alba Berlin", //(Basketball)
            "Eisbären Berlin", //(Eishockey)
            "Füchse Berlin", //(Handball)
            "SG Dynamo Dresden", //(Fußball - 3. Liga)
            "Rhein-Neckar Löwen", //(Handball)
            "THW Kiel", //(Handball)
            "SC Magdeburg", //(Handball)
            "EWE Baskets Oldenburg", //(Basketball)
            "Iserlohn Roosters", //(Eishockey)
            "FC Bayern München", //(Fußball)
            "Brose Bamberg", //(Basketball)
            "Borussia Dortmund", //(Fußball)
            "Bayer 04 Leverkusen", //(Fußball)
            "Adler Mannheim", //(Eishockey)
            "HSG Wetzlar", //(Handball)
            "HC Empor Rostock", //(Handball)
            "1. FC Köln", //(Fußball)
            "TSV 1860 München", //(Fußball)
            "ERC Ingolstadt", //(Eishockey)
            "DJK Rimpar Wölfe", //(Handball)
            "Telekom Baskets Bonn", //(Basketball)
            "Eispiraten Crimmitschau", //(Eishockey)
            "Eintracht Frankfurt", //(Fußball)
            "TVB Stuttgart", //(Handball)
            "SC Rasta Vechta", //(Basketball)
            "SG Flensburg-Handewitt", //(Handball)
            "NINERS Chemnitz", //(Basketball)
            "SV Darmstadt 98", //(Fußball)
            "EHC Red Bull München", //(Eishockey)
            "SV Werder Bremen" //(Fußball)
        )
        return randomClub.random()
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

    private fun getTime(): String {
        val time = listOf(
            "Heute, 08:15 Uhr",
            "Gestern, 20:45 Uhr",
            "Montag, 14:30 Uhr",
            "Dienstag, 09:12 Uhr",
            "Mittwoch, 18:57 Uhr",
            "Donnerstag, 12:08 Uhr",
            "Freitag, 16:40 Uhr",
            "Samstag, 10:20 Uhr",
            "Sonntag, 21:05 Uhr",
            "Vor einer Stunde",
            "Vor 30 Minuten",
            "Vor 2 Stunden",
            "Vor 45 Minuten",
            "Gerade eben",
            "Vor 5 Stunden",
            "Gestern, 23:59 Uhr",
            "Vor 15 Minuten",
            "Vor 3 Stunden",
            "Vor 1 Minute",
            "Heute früh, 06:00 Uhr",
            "Letzte Woche, Montag",
            "Vor 8 Stunden",
            "Morgen, 07:30 Uhr",
            "In 2 Tagen, 14:00 Uhr",
            "In 3 Stunden",
        )
        return time.random()
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

    private fun randomMessage(): String {
        val randomMessage = listOf(
            "Warum war der Basketball so schlecht in der Schule? Weil er immer in der Halle abhing!",
            "Wie nennt man einen Fisch, der Tennis spielt? Einen Volleyfisch!",
            "Warum hat der Golfer zwei Paar Hosen an? Für den Fall, dass er ein Loch in eins spielt!",
            "Warum hat der Football-Spieler keine Zeit für Mathematik? Weil er immer Probleme mit den Touchdowns hat!",
            "Warum war der Fußball so traurig? Weil er immer ins Netz ging!",
            "Warum sind Geister so schlecht beim Lügen? Weil man durch sie hindurchsehen kann – besonders wenn sie beim Tischtennis schummeln!",
            "Wie nennt man einen Marathonläufer mit einem Keks im Mund? Einen Langstreckenkeks!",
            "Warum hat der Radfahrer immer eine Extra-Jacke dabei? Weil er in die Kurve gelegt hat!",
            "Warum hat der Tennisspieler einen so guten Aufschlag? Weil er sein Auto gut parken kann!",
            "Warum hat der Boxer immer seinen Pass dabei? Weil er immer über die Grenze schlägt!",
            "Guten Morgen, Team! Wer startet heute den Tag mit einem Energieschub – Kaffee oder Tee?",
            "Guten Mittag, alle zusammen! Was steht auf eurem Mittagsmenü oder gibt es Empfehlungen für eine leckere Pause?",
            "Hallo ihr Lieben, einen sonnigen Morgen! Welche Pläne habt ihr für den Tag, um ihn besonders produktiv zu gestalten?",
            "Guten Mittag, Team! Wer hat Tipps für ein schnelles, aber leckeres Mittagessen? Teilt eure kulinarischen Geheimnisse!",
            "Guten Morgen, Gruppe! Welche Musik begleitet euch heute auf dem Weg zur Arbeit oder beim Home Office? Lasst uns eine Playlist erstellen!",
            "Hallo Lisa, ich hoffe, es geht dir gut! Könntest du bitte die letzten Verkaufsdaten überprüfen und mir einen kurzen Überblick geben? Danke!",
            "Guten Morgen, Thomas! Ich wollte nur sicherstellen, dass du die neuesten Projektunterlagen erhalten hast. Falls nicht, lass es mich wissen. Danke!",
            "Hi Julia, ich hoffe, dein Tag läuft bisher reibungslos! Könntest du mir kurz Feedback zu den letzten Marketingvorschlägen geben? Deine Meinung ist mir wichtig. Danke!",
            "Hallo Michael, ich hoffe, du hattest einen guten Start in den Tag! Wir müssen uns dringend zusammensetzen, um die Budgetplanung für das nächste Quartal zu besprechen. Wann passt es dir?",
            "Guten Morgen, Sarah! Ich habe gesehen, dass du gestern an dem Bericht gearbeitet hast. Könntest du mir bitte eine kurze Zusammenfassung der wichtigsten Ergebnisse schicken? Vielen Dank!",
            "Hey Team, was für ein Spiel gestern, oder? Unsere Mannschaft hat es gerockt! 🚀 Wer hat es gesehen?!",
            "Moin zusammen! Falls es jemand verpasst hat: Unser Lieblings-Team hat gestern wieder gewonnen! 🎉🏀 Wie waren eure Reaktionen?",
            "Hallo Sportfans! Ganz knappes Rennen gestern, aber wir haben es geschafft! 🏁 Wie habt ihr mitgefiebert?",
            "Guten Abend, Leute! Unser Team hat gestern eine beeindruckende Leistung gezeigt. Welches war euer Lieblingsmoment im Spiel?",
            "Hi alle! Habt ihr das Ergebnis vom gestrigen Match gesehen? Einfach der Wahnsinn! ⚽️ Teilt eure Gedanken und Emotionen!",
            "Ja, das Spiel war der Wahnsinn! Tolle Leistung unserer Mannschaft. 🏀",
            "Moin! Leider habe ich es verpasst, aber herzlichen Glückwunsch an unser Team! 🎉 Wer hat besonders herausgeragt?",
            "Hallo! Ich habe das Rennen gesehen – so spannend! 🏁 Hat jemand besonders beeindruckt?",
            "Guten Abend! Der Sieg gestern war fantastisch. Mein Lieblingsmoment? Definitiv das entscheidende Tor! ⚽️",
            "Hi! Leider habe ich das Spiel verpasst. Was war das Highlight für euch? 🤔",
            "Ja, ich habe mitgefiebert! Das Rennen war wirklich knapp. Welches Team hat eurer Meinung nach den Unterschied gemacht? 🏎️",
            "Absolut! Unsere Mannschaft hat gestern großartig gespielt. Wer war euer MVP? 🌟",
            "Hey! Das Ergebnis vom gestrigen Match war der Hammer. Welcher Spieler hat euch am meisten beeindruckt? 🤩",
            "Ja, ich habe es gesehen! Der Sieg war hart erkämpft. Was denkt ihr, wie steht es um unsere Chancen in der nächsten Runde? 🤔",
            "Hallo! Das Spiel gestern war wirklich aufregend. Welche Taktik hat unser Team eurer Meinung nach zum Erfolg geführt? ⚾️"
            )
        return randomMessage.random()
    }
}
