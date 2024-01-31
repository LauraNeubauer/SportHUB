package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ClubFragmentBinding
import com.example.myapplication.viewmodel.MainViewModel

// Die Klasse für das Fragment mit den Vereinen
class ClubFragment : Fragment() {

    //die benötigten Variablen für das Binding und das ViewModel
    private lateinit var binding: ClubFragmentBinding
    private val vielmodel: MainViewModel by activityViewModels()

    // Wird aufgerufen, um die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflatiere das Layout für dieses Fragment
        binding = ClubFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Beobachte Änderungen im aktuellen Club im ViewModel und aktualisiere die Ansicht entsprechend
        vielmodel.getCurrentClub.observe(viewLifecycleOwner){

            // Setze die Werte der Club-Details in die entsprechenden Views
            binding.tvClubName.text = it.name
            binding.btParticipants.text = it.participants.toString()
            binding.btSportsOne.text = it.sport
            binding.tvBio.text = it.bio
            binding.tvGroupOne.text = it.name

            // Setze die Statistikwerte in die entsprechenden Views
            binding.tvStatEst.text = it.est.toString()
            binding.tvStatsLigen.text = it.ligen.toString()
            binding.tvStatsPokale.text = it.pokale.toString()
            binding.tvStatsQuote.text = it.Quote.toString()
            binding.tvStatsTuniere.text = it.tuniere.toString()

        }

        // Navigiere zum HomeFragment, wenn der "Zurück"-Button geklickt wird
        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

    }
}