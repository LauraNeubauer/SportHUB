package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

   private lateinit var binding : HomeFragmentBinding
   var datasetEvents = ExampleDatabase().loadEvents()
   var datasetNews = ExampleDatabase().loadNews()

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = HomeFragmentBinding.inflate(layoutInflater)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding.fbResults.setOnClickListener {
         findNavController().navigate(R.id.action_homeFragment_to_resultsFragment)
      }

      binding.btClub.setOnClickListener {
         findNavController().navigate(R.id.action_homeFragment_to_clubFragment)
      }

   }
}

