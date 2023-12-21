package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.MainViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.adapter.ChatAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ChatFragmentBinding

class ChatFragment : Fragment() {


    private lateinit var binding : ChatFragmentBinding
    var datasetChats = ExampleDatabase().loadChats()
    private val PersonVM : PersonViewModel by activityViewModels()
    private val viewModel : MainViewModel by activityViewModels()

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

        binding.rvChats.adapter = ChatAdapter(datasetChats, PersonVM, viewModel)

    }
}