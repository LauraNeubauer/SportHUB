package com.example.myapplication.ui

import Groups
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.PersonApi.ChatViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.adapter.AllGroupsAdatper
import com.example.myapplication.databinding.AllChatsFragmentBinding

class AllChatsFragment : Fragment() {


    private lateinit var binding: AllChatsFragmentBinding
    private val personViewModel: PersonViewModel by activityViewModels()
    private val chatViewModel: ChatViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllChatsFragmentBinding.inflate(layoutInflater)
        chatViewModel.loadChats()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupsList: List<Groups> = listOf(
            Groups("FitFam Legends", true),
            Groups("Muscle Mavericks", true),
            Groups("Sweat Sesh Squad", true),
            Groups("Iron Tribe", true),
            Groups("Flex Fusion Crew", true),
            Groups("Powerhouse Pioneers", true),
            Groups("Adrenaline Allies", true),
            Groups("Ripped Rebels", true),
            Groups("Beast Mode Brigade", true),
            Groups("Elevate Collective", true),
            Groups("Warrior Workout", true),
            Groups("Grit Gang", true),
            Groups("Zen & Zeal Crew", true),
            Groups("Sculpted Syndicate", true),
            Groups("Pump Posse", true)
        )

        binding.rvChats.adapter = AllGroupsAdatper(groupsList)

    }
}