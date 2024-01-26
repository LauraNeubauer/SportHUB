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
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.HomeFragmentBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    fun generateCurrentDate(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
        return today.format(formatter)
    }
}

