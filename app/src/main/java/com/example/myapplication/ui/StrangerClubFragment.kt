package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.StrangerClubFragmentBinding
import com.example.myapplication.viewmodel.MainViewModel

class StrangerClubFragment : Fragment() {

    // die benötigten Variablen für das Binding und des ViewModels
    private lateinit var binding: StrangerClubFragmentBinding
    private val vielmodel: MainViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StrangerClubFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Beobachte Änderungen am aktuellen Club im MainViewModel
        vielmodel.getCurrentClub.observe(viewLifecycleOwner){

            // Setze die Ansichten entsprechend der Clubdaten
            binding.tvClubName.text = it.name
            binding.btParticipants.text = it.participants.toString()
            binding.btSportsOne.text = it.sport
            binding.tvBio.text = it.bio
            binding.tvGroupOne.text = it.name

            binding.tvStatEst.text = it.est.toString()
            binding.tvStatsLigen.text = it.ligen.toString()
            binding.tvStatsPokale.text = it.pokale.toString()
            binding.tvStatsQuote.text = it.Quote.toString()
            binding.tvStatsTuniere.text = it.tuniere.toString()

        }

        // Reagiere auf Klicks auf die Schaltfläche "Zurück"
        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.finderFragment)
        }

    }
}