package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ChatDetailFragmentBinding

class ChatDetailFragment : Fragment() {

    private lateinit var binding : ChatDetailFragmentBinding
    var datasetChats = ExampleDatabase().loadChats()
    private val personViewModel : ViewModel by activityViewModels()

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



        // Chatgruppennamen in der Chatdatabase
        // namen der teilnehmer in der persondatabase
        // zeit der gesendeten nachrichten und die nachrichten in der ChatDatabase

        //Query um die Personen zu lasen die in der Gruppe sind -> PersonDao
        //if um die namen zuzuweisen der nachrichten für die Chatdatabase (im ChatRepository)
        //lachmessage im chatDatabase muss zuletzt angezeigt werden

    }
}