package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.adapter.ChatAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ChatFragmentBinding

class ChatFragment : Fragment() {


    private lateinit var binding : ChatFragmentBinding
    var datasetChats = ExampleDatabase().loadChats()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChatFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvChats.adapter = ChatAdapter(datasetChats)
    }
}