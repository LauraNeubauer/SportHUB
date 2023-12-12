package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.EventHomeAdapter
import com.example.myapplication.adapter.NewsHomeAdapter
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

      binding.rvEvents.adapter = EventHomeAdapter(datasetEvents)
      binding.rvHomeNews.adapter = NewsHomeAdapter(datasetNews)

      binding.floatingActionButton4.setOnClickListener {
         findNavController().navigate(R.id.action_homeFragment_to_finderFragment)
      }

      binding.floatingActionButton5.setOnClickListener {
         findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
      }

      binding.floatingActionButton6.setOnClickListener {
         findNavController().navigate(R.id.action_homeFragment_to_profilFragment)
      }

      binding.floatingActionButton7.setOnClickListener {
         findNavController().navigate(R.id.action_homeFragment_to_clubFragment)
      }

   }
}

