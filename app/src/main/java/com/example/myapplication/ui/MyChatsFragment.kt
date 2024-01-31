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

// Meine Chats
class MyChatsFragment : Fragment() {

    //die benötigten Variablen für das Binding und die ViewModels
    private lateinit var binding: MyChatsFragmentBinding
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflatiere das Layout für dieses Fragment
        binding = MyChatsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setze einen Click-Listener für den Button "Groups" zum Navigieren zu allen Chats
        binding.btGroups.setOnClickListener {
            findNavController().navigate(R.id.action_myChatsFragment_to_allChatsFragment)
        }

        // Starte das Abrufen der eigenen Chats über das ViewModel
        firebaseViewModel.fetchMyChats() // Start fetching the data

        // Überwache Änderungen an den eigenen Chats und aktualisiere den RecyclerView-Adapter
        firebaseViewModel.myChats.observe(viewLifecycleOwner) {
            binding.rvChats.adapter = MyChatsAdapter(it, firebaseVM = firebaseViewModel)
        }
    }
}