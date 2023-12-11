package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.adapter.eventAdapter
import com.example.myapplication.data.exampleDatabase
import com.example.myapplication.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

   private lateinit var binding : HomeFragmentBinding
   var dataset = exampleDatabase().loadEvents()

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

      binding.rvEvents.adapter = eventAdapter(dataset)
   }
}

