package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.R
import com.example.myapplication.adapter.EventHomeAdapter
import com.example.myapplication.adapter.NewsHomeAdapter
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    var datasetEvents = ExampleDatabase().loadEvents()
    var datasetNews = ExampleDatabase().loadNews()
    private val viewModel: ViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        viewModel.loadPersons()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().navigate(R.id.onBoardingOneFragment)
            }
        }

        binding.logout.setOnClickListener {
            firebaseViewModel.logout()
        }

        binding.fbResults.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_resultsFragment)
        }

        binding.btClub.setOnClickListener {
            viewModel.clubdatabase.observe(viewLifecycleOwner) {
                viewModel.setCurrentClub(it[1])
                findNavController().navigate(R.id.action_homeFragment_to_clubFragment)
            }
        }

        binding.rvHome.adapter = EventHomeAdapter(datasetEvents, viewModel)
        binding.rvNews.adapter = NewsHomeAdapter(datasetNews)

        val clubs = ClubDatabase().getClubs().value

        if (clubs != null && clubs.size >= 2) {
            // Teile die Liste in zwei Hälften
            val firstHalf = clubs.subList(0, clubs.size / 2)
            val secondHalf = clubs.subList(clubs.size / 2, clubs.size)

            // Zufällige Auswahl von Clubs für die Gruppen
            val randomClubGroup1 = firstHalf.random()
            val randomClubGroup2 = secondHalf.random()

            // Zufällige Anzahl von Toren für Gruppe 2
            val randomGoalsGroup2 = listOf(1, 2, 2, 1, 4, 2, 3, 6, 5).random()

            // Setze die Werte in die UI-Elemente
            binding.tvGroupOne.text = randomClubGroup1.name
            binding.tvGroupTwo.text = randomClubGroup2.name
            binding.tvGroupOneGoals.text = randomGoalsGroup2.toString()
            binding.tvGroupTwoGoals.text = randomGoalsGroup2.toString()

        }

       var events = ExampleDatabase().ResultList

       binding.tvLeague.text = events.random().title
       binding.tvLand.text = events.random().place

       binding.tvTime.text = listOf<Int>(29,34,36,38,41,43,45,47,50,53,56,61,64,67,69,73,75,77,83,84,88,90).random().toString()

    }
}

