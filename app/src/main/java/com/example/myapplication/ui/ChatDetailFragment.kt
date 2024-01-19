package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.adapter.ChatDetailAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ChatDetailFragmentBinding
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ChatDetailFragment : Fragment() {

    private lateinit var binding: ChatDetailFragmentBinding
    var datasetChats = ExampleDatabase().loadChats()
    private val personViewModel: ViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChatDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseViewModel.fetchMyChats()

        firebaseViewModel.getCurrentChat.observe(viewLifecycleOwner) {
            binding.tvChatName.text = it.groupName
            binding.rvMessages.adapter = ChatDetailAdapter(it.messages, personViewModel)
        }
    }

    fun getCurrentTime(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return currentTime.format(formatter)
    }
}