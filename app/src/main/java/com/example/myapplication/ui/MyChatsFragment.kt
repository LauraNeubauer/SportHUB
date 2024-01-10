package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PersonApi.ChatViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.R
import com.example.myapplication.adapter.ChatAdapter
import com.example.myapplication.databinding.MyChatsFragmentBinding

class MyChatsFragment : Fragment() {


    private lateinit var binding : MyChatsFragmentBinding
    private val personViewModel : PersonViewModel by activityViewModels()
    private val chatViewModel : ChatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyChatsFragmentBinding.inflate(layoutInflater)
        chatViewModel.loadChats()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel.chats.observe(viewLifecycleOwner) {
            binding.rvChats.adapter = ChatAdapter(it, chatViewModel, personViewModel)
        }

        binding.btGroups.setOnClickListener {
            findNavController().navigate(R.id.action_myChatsFragment_to_allChatsFragment)
        }
    }
}