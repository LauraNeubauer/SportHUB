package com.example.myapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.PersonApi.PersonRepository
import com.example.myapplication.PersonApi.local.getDatabase
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.R
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.model.Club
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Datenbank-Instanz und Repository für Personen
    private val database = getDatabase(application)
    private val repo = PersonRepository(database)

    // LiveData für die Club-Datenbank
    val clubdatabase: LiveData<List<Club>> = ClubDatabase().getClubs()

    // LiveData für die Liste der Personen
    val contacts = repo.personenListe

    // LiveData für den aktuellen ausgewählten Club und das aktuelle Profil
    private val _currentClub = MutableLiveData<Club>()
    private val _currentProfile = MutableLiveData<PersonData>()

    val getCurrentClub: MutableLiveData<Club>
        get() = _currentClub
    val currentProfile: MutableLiveData<PersonData>
        get() = _currentProfile

    // Setzt den aktuellen ausgewählten Club
    fun setCurrentClub(club: Club) {
        _currentClub.postValue(club)
    }

    // Setzt das aktuelle Profil
    fun setCurrentProfile(profile: PersonData) {
        _currentProfile.postValue(profile)
    }

    // Initialisierung beim Erstellen der Instanz
    init {
        loadPersons()
    }

    // Enumeration für Level
    enum class Level {
        BEGINNER, IMPROVER, ADVANCED, EXPERT, ELITE, ALLE, LEVEL
    }

    // Enumeration für Sportarten
    enum class Sports {
        BADMINTON, SQUASH, TISCHTENNIS, TENNIS, FUSSBALL, HOCKEY, CRICKET, HANDBALL, ALLE, SPORTS
    }

    // Funktion zum Filtern und Sortieren von Personen
    fun filterAndSort(
        level: Level? = null,
        sports: Sports? = null,
        sortBy: String? = null,
        originalList: MutableList<PersonData>,
    ): List<PersonData> {
        var filteredList = originalList

        filteredList = when (level) {
            Level.BEGINNER -> filteredList.filter { it.level == "BEGINNER" }.toMutableList()
            Level.IMPROVER -> filteredList.filter { it.level == "IMPROVER" }.toMutableList()
            Level.ADVANCED -> filteredList.filter { it.level == "ADVANCED" }.toMutableList()
            Level.EXPERT -> filteredList.filter { it.level == "EXPERT" }.toMutableList()
            Level.ELITE -> filteredList.filter { it.level == "ELITE" }.toMutableList()
            Level.ALLE -> filteredList
            Level.LEVEL -> filteredList
            else -> originalList
        }

        filteredList = when (sports) {
            Sports.BADMINTON -> filteredList.filter { it.sportsOne == "BADMINTON" }.toMutableList()
            Sports.SQUASH -> filteredList.filter { it.sportsOne == "SQUASH" }.toMutableList()
            Sports.TISCHTENNIS -> filteredList.filter { it.sportsOne == "TISCHTENNIS" }.toMutableList()
            Sports.TENNIS -> filteredList.filter { it.sportsOne == "TENNIS" }.toMutableList()
            Sports.FUSSBALL -> filteredList.filter { it.sportsOne == "FUSSBALL" }.toMutableList()
            Sports.HOCKEY -> filteredList.filter { it.sportsOne == "HOCKEY" }.toMutableList()
            Sports.CRICKET -> filteredList.filter { it.sportsOne == "CRICKET" }.toMutableList()
            Sports.HANDBALL -> filteredList.filter { it.sportsOne == "HANDBALL" }.toMutableList()
            Sports.ALLE -> filteredList
            Sports.SPORTS -> filteredList
            else -> originalList
        }

        filteredList = when (sortBy) {
            "ENTFERNUNG" -> filteredList.sortedBy { it.entfernung }.toMutableList()
            "DATUM" -> filteredList.sortedBy { it.date }.toMutableList()
            "ALTER" -> filteredList.sortedByDescending { it.age.toInt() }.toMutableList()
            "GRÖSSE" -> filteredList.sortedByDescending { it.size.toInt() }.toMutableList()
            "MATCHES" -> filteredList.sortedByDescending { it.matches.toInt() }.toMutableList()
            "WINS" -> filteredList.sortedByDescending { it.wins.toInt() }.toMutableList()
            "POKALE" -> filteredList.sortedByDescending { it.trophys.toInt() }.toMutableList()
            "ALLE" -> filteredList
            "SORT" -> filteredList
            else -> originalList
        }

        return filteredList
    }

    // Funktion zum Filtern und Sortieren von Clubs
    fun filterAndSortClubs(
        sports: Sports? = null,
        sortBy: String? = null,
        originalList: MutableList<Club>,
    ): MutableList<Club> {
        var filteredList = originalList

        filteredList = when (sports) {
            Sports.BADMINTON -> filteredList.filter { it.sport == "BADMINTON" }.toMutableList()
            Sports.SQUASH -> filteredList.filter { it.sport == "SQUASH" }.toMutableList()
            Sports.TISCHTENNIS -> filteredList.filter { it.sport == "TISCHTENNIS" }.toMutableList()
            Sports.TENNIS -> filteredList.filter { it.sport == "TENNIS" }.toMutableList()
            Sports.FUSSBALL -> filteredList.filter { it.sport == "FUSSBALL" }.toMutableList()
            Sports.HOCKEY -> filteredList.filter { it.sport == "HOCKEY" }.toMutableList()
            Sports.CRICKET -> filteredList.filter { it.sport == "CRICKET" }.toMutableList()
            Sports.HANDBALL -> filteredList.filter { it.sport == "HANDBALL" }.toMutableList()
            Sports.ALLE -> originalList
            Sports.SPORTS -> originalList
            else -> originalList
        }

        filteredList = when (sortBy) {
            "ENTFERNUNG" -> filteredList.sortedBy { it.entfernung }.toMutableList()
            "EST" -> filteredList.sortedBy { it.est }.toMutableList()
            "QUOTE" -> filteredList.sortedByDescending { it.Quote }.toMutableList()
            "POKALE" -> filteredList.sortedByDescending { it.pokale }.toMutableList()
            "TUNIERE" -> filteredList.sortedByDescending { it.tuniere }.toMutableList()
            "LIGEN" -> filteredList.sortedByDescending { it.ligen }.toMutableList()
            "ALLE" -> filteredList
            "SORT" -> filteredList
            else -> originalList
        }

        return filteredList
    }

    // Funktion zum Laden von Personen
    fun loadPersons() {
        viewModelScope.launch {
            repo.getPerson()
            Log.d("TAG", "Person ausgegeben")
        }
    }

    // Funktion zum Einfügen einer Person in die Datenbank
    fun insertPerson(profile: PersonData) {
        viewModelScope.launch {
            try {
                val personData = PersonData(
                    name = profile.name,
                    gender = profile.gender,
                    age = profile.age,
                    pic = profile.pic,
                    trophys = "0",
                    matches = "0",
                    wins = "0",
                    size = profile.size,
                    level = profile.level,
                    sportsOne = profile.sportsOne,
                    sportsTwo = profile.sportsTwo,
                    bio = profile.bio,
                    date = profile.date
                )
                repo.insertPerson(personData)
            } catch (e: Exception) {
                Log.e("Person nicht hinzugefügt", "Nicht Hinzugefügt")
            }
        }
    }

    // LiveData für den aktuellen Bildindex in der ImageList
    private val _currentImageIndex = MutableLiveData<Int>()
    val currentImageIndex: LiveData<Int> get() = _currentImageIndex

    // Liste von Drawable-Ressourcen für Anzeigen
    val imageList = listOf(
        R.drawable.ad_1,
        R.drawable.ad_2,
        R.drawable.ad_3,
        R.drawable.ad_4,
        R.drawable.ad_5,
        R.drawable.ad_6,
    )

    // Variable für den aktuellen Index und Intervall für den Bildwechsel
    private var currentIndex = 0
    private val intervalMillis = 20000

    // Initialisierung für den automatischen Bildwechsel
    init {
        startImageChange()
    }

    // Funktion für den automatischen Bildwechsel
    private fun startImageChange() {
        Thread {
            while (true) {
                Thread.sleep(intervalMillis.toLong())
                currentIndex = (currentIndex + 1) % imageList.size
                _currentImageIndex.postValue(currentIndex)
            }
        }.start()
    }
}

