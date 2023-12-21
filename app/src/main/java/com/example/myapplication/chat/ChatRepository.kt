package com.example.myapplication.PersonApi

import com.example.myapplication.PersonApi.local.ChatDatabase
import com.example.myapplication.PersonApi.model.ChatData
import com.example.myapplication.R

class ChatRepository(
    private val chatdb: ChatDatabase
) {
    val chatList = chatdb.chatDao.getAll()

    suspend fun getChat() {
        val groupNames = getUniqueGroupNames()
        for (groupName in groupNames) {
            val chatData = ChatData(
                groupname = groupName,
                from = null,
                pic = randomPic(),
                lastChatter = "Fritz",
                lastMessage = randomMessage(),
                time = getTime()
            )
            chatdb.chatDao.insertMessage(chatData)
        }
    }

    private fun getUniqueGroupNames(): List<String> {
        return listOf(
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

    fun randomPic() : Int {
        val piclist = listOf(
            R.drawable.balls, R.drawable.balls_bg, R.drawable.alex__aol4_fdq3m_unsplash, R.drawable.crash_test_m, R.drawable.images, R.drawable.images__1_, R.drawable.images__2_
        )
        return piclist.random()
    }
}
