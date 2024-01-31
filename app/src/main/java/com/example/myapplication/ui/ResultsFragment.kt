package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.ResultsAdapter
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.databinding.ResultsFragmentBinding
import com.example.myapplication.viewmodel.MainViewModel

class ResultsFragment : Fragment() {

    // die benötigten Variablen für das Binding und des ViewModels und laden der Daten
    private lateinit var binding : ResultsFragmentBinding
    var dataset = ClubDatabase().getClubs()
    private val mainViewModel: MainViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ResultsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Beobachte Änderungen am aktuellen Bildindex im MainViewModel
        mainViewModel.currentImageIndex.observe(viewLifecycleOwner) { index ->
            binding.ads.setImageResource(mainViewModel.imageList[index])
        }

        // Beobachte Änderungen an der Clubdatenbank im MainViewModel
        mainViewModel.clubdatabase.observe(viewLifecycleOwner){
            // Konvertiere die Datenbank in eine bearbeitbare Liste
            var list = mainViewModel.clubdatabase.value!!.toMutableList()
            // Setze den Adapter für das RecyclerView
            binding.rvResults.adapter = ResultsAdapter(list)
        }

        // Navigiere zur HomeFragment, wenn die Schaltfläche "Zurück" geklickt wird
        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

    }
}

