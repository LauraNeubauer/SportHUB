package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.adapter.ResultsAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ResultsFragmentBinding

class ResultsFragment : Fragment() {

    private lateinit var binding : ResultsFragmentBinding
    var dataset = ExampleDatabase().loadResults()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ResultsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvResults.adapter = ResultsAdapter(dataset)
    }
}

