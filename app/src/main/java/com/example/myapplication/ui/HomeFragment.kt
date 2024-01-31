package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.EventHomeAdapter
import com.example.myapplication.adapter.NewsHomeAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.HomeFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel
import com.example.myapplication.viewmodel.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Klasse des Home Fragmentes
class HomeFragment : Fragment() {

    //die benötigten Variablen für das Binding und die ViewModels
    private lateinit var binding: HomeFragmentBinding
    var datasetEvents = ExampleDatabase().loadEvents()
    var datasetNews = ExampleDatabase().loadNews()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Wird aufgerufen, um die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflatiere das Layout für dieses Fragment
        binding = HomeFragmentBinding.inflate(layoutInflater)
        // Lade Personen (Nutzer) über das ViewModel
        mainViewModel.loadPersons()
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Überwache Änderungen am aktuellen Nutzer in Firebase und navigiere zum Onboarding, falls nicht angemeldet
        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().navigate(R.id.onBoardingOneFragment)
            }
        }

        // Setze Click-Listener für Logout, Ergebnisse und Clubansicht
        binding.logout.setOnClickListener {
            firebaseViewModel.logout()
        }

        binding.fbResults.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_resultsFragment)
        }

        binding.btClub.setOnClickListener {
            // Navigiere zur Clubansicht und setze den aktuellen Club im ViewModel
            mainViewModel.clubdatabase.observe(viewLifecycleOwner) {
                mainViewModel.setCurrentClub(it[1])
                findNavController().navigate(R.id.action_homeFragment_to_clubFragment)
            }
        }

        // Setze Adapter für Event- und News-RecyclerView
        binding.rvHome.adapter = EventHomeAdapter(datasetEvents, mainViewModel)
        binding.rvNews.adapter = NewsHomeAdapter(datasetNews)

        // Setze statische Werte für eine Beispiel-Sportveranstaltung
        binding.tvGroupOne.text = "FC St. Pauli"
        binding.tvGroupTwo.text = "Nürnberger SV"
        binding.tvGroupOneGoals.text = "1"
        binding.tvGroupTwoGoals.text = "2"
        binding.tvLeague.text = "2ste Liga"
        binding.tvSport.text = "FUSSBALL"
        binding.tvTime.text = "61"
        binding.tvDate.text = ("HEUTE, " + generateCurrentDate())
        binding.grouponepic.setImageResource(R.drawable.download)
        binding.grouptwopic.setImageResource(R.drawable.download__1_)
    }

    // Funktion zur Generierung des aktuellen Datums im Format "dd.MM.yy"
    fun generateCurrentDate(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
        return today.format(formatter)
    }
}

