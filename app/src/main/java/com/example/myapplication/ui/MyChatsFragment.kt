package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.MyChatsAdapter
import com.example.myapplication.databinding.MyChatsFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel

class MyChatsFragment : Fragment() {


    private lateinit var binding: MyChatsFragmentBinding
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyChatsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btGroups.setOnClickListener {
            findNavController().navigate(R.id.action_myChatsFragment_to_allChatsFragment)
        }

        firebaseViewModel.fetchMyChats() // Start fetching the data

        firebaseViewModel.myChats.observe(viewLifecycleOwner) {
            binding.rvChats.adapter = MyChatsAdapter(it, firebaseVM = firebaseViewModel)
        }
    }
}