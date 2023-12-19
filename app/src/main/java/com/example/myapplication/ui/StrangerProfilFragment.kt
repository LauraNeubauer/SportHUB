package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.StrangerProfilFragmentBinding

class StrangerProfilFragment : Fragment() {

    private lateinit var binding : StrangerProfilFragmentBinding
    var datasetEvents = ExampleDatabase().loadEvents()
    var datasetNews = ExampleDatabase().loadNews()
    private val viewModel : PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StrangerProfilFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentProfile.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
        }
    }
}