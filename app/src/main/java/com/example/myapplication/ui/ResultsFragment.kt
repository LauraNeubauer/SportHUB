package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.ResultsAdapter
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.databinding.ResultsFragmentBinding
import com.example.myapplication.viewmodel.MainViewModel

class ResultsFragment : Fragment() {

    private lateinit var binding : ResultsFragmentBinding
    var dataset = ClubDatabase().getClubs()
    private val mainViewModel: MainViewModel by activityViewModels()

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

        mainViewModel.currentImageIndex.observe(viewLifecycleOwner) { index ->
            binding.ads.setImageResource(mainViewModel.imageList[index])
        }

        mainViewModel.clubdatabase.observe(viewLifecycleOwner){
            var list = mainViewModel.clubdatabase.value!!.toMutableList()
            binding.rvResults.adapter = ResultsAdapter(list)
        }

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

    }
}

