package com.example.myapplication.ui

import Groups
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Firebase.FirebaseViewModel
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
            Groups("1", "FitFam Legends", true),
            Groups("2", "Muscle Mavericks", true),
            Groups("3","Sweat Sesh Squad", true),
            Groups("4","Iron Tribe", true),
            Groups("5","Flex Fusion Crew", true),
            Groups("6","Powerhouse Pioneers", true),
            Groups("7","Adrenaline Allies", true),
            Groups("8","Ripped Rebels", true),
            Groups("9","Beast Mode Brigade", true),
            Groups("10","Elevate Collective", true),
            Groups("11","Warrior Workout", true),
            Groups("12","Grit Gang", true),
            Groups("13","Zen & Zeal Crew", true),
            Groups("14","Sculpted Syndicate", true),
            Groups("15","Pump Posse", true)
        )

        binding.rvChats.adapter = AllGroupsAdatper(groupsList, firebaseVM = FirebaseViewModel())

    }
}