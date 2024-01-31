package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.AllGroupsAdatper
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.AllChatsFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel
import com.example.myapplication.viewmodel.MainViewModel

//Die Klasse der Allgemeinen speicherbaren GruppenChats
class AllChatsFragment : Fragment() {

    //die benötigten Variablen für das Binding und das ViewModel
    private lateinit var binding: AllChatsFragmentBinding
    private val mainViewModel : MainViewModel by activityViewModels()

    // Wird aufgerufen, um die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflatiere das Layout für dieses Fragment
        binding = AllChatsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lade Chat-Daten aus der Beispiel-Datenbank
        val dataset = ExampleDatabase().loadChats()

        // Richte RecyclerView mit dem Adapter und FirebaseViewModel ein
        binding.rvChats.adapter = AllGroupsAdatper(dataset, firebaseVM = FirebaseViewModel())

        // Navigiere zu MyChatsFragment, wenn der Button "Meine Chats" geklickt wird
        binding.btMyChats.setOnClickListener {
            findNavController().navigate(R.id.action_allChatsFragment_to_myChatsFragment)
        }
        // Navigiere zu MyChatsFragment, wenn der Zurück-Button geklickt wird
        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_allChatsFragment_to_myChatsFragment)
        }

    }
}