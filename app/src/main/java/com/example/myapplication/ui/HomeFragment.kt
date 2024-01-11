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

class HomeFragment : Fragment() {

   private lateinit var binding : HomeFragmentBinding
   var datasetEvents = ExampleDatabase().loadEvents()
   var datasetNews = ExampleDatabase().loadNews()
   private val viewModel : ViewModel by activityViewModels()
   private val firebaseViewModel : FirebaseViewModel by activityViewModels()

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
         findNavController().navigate(R.id.action_homeFragment_to_clubFragment)
      }

      binding.rvHome.adapter = EventHomeAdapter(datasetEvents, viewModel)
      binding.rvNews.adapter = NewsHomeAdapter(datasetNews)

   }
}

