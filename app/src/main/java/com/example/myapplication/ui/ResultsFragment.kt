package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.R
import com.example.myapplication.adapter.ResultsAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ResultsFragmentBinding

class ResultsFragment : Fragment() {

    private lateinit var binding : ResultsFragmentBinding
    var dataset = ExampleDatabase().loadResults()
    private val viewModel: ViewModel by activityViewModels()

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

        viewModel.currentImageIndex.observe(viewLifecycleOwner) { index ->
            binding.ads.setImageResource(viewModel.imageList[index])
        }

        binding.rvResults.adapter = ResultsAdapter(dataset)

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}

