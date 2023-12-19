package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.adapter.EventHomeAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.CalenderFragmentBinding

class CalenderFragment : Fragment() {

    private lateinit var binding : CalenderFragmentBinding
    var datasetEvents = ExampleDatabase().loadEvents()
    private val viewModel : PersonViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CalenderFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEvents.adapter = EventHomeAdapter(datasetEvents, viewModel)

    }
}