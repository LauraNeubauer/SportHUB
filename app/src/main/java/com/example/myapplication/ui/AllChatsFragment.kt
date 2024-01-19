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
import com.example.myapplication.adapter.AllGroupsAdatper
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.AllChatsFragmentBinding

class AllChatsFragment : Fragment() {


    private lateinit var binding: AllChatsFragmentBinding
    private val viewModel : ViewModel by activityViewModels()
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllChatsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataset = ExampleDatabase().loadChats()

        binding.rvChats.adapter = AllGroupsAdatper(dataset, firebaseVM = FirebaseViewModel(), viewModel)

        binding.btChats.setOnClickListener {
            findNavController().navigate(R.id.action_allChatsFragment_to_myChatsFragment)
        }

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_allChatsFragment_to_myChatsFragment)
        }

    }
}