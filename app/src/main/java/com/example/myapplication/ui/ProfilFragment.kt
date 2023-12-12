package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.adapter.ProfilAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ProfilFragmentBinding

class ProfilFragment : Fragment() {

    lateinit var binding: ProfilFragmentBinding
    var datasetPersons = ExampleDatabase().loadPersons()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfilFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = datasetPersons

        binding.rvProfil.adapter = ProfilAdapter(item)

    }
}