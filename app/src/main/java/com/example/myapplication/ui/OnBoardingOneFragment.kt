package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.OnboardingOneFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel

class OnBoardingOneFragment : Fragment() {

    //die benötigten Variablen für das Binding und des ViewModel
    private lateinit var binding : OnboardingOneFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflatiere das Layout für dieses Fragment
        binding = OnboardingOneFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Überwache Änderungen im aktuellen Benutzerstatus im FirebaseViewModel
        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            // Navigiere zum HomeFragment, wenn ein Benutzer angemeldet ist
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        // Setze einen Click-Listener für den "Login"-Button, um zum Registrierungs-Fragment zu navigieren
        binding.btLogin.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingOneFragment_to_registrateFragment)
        }

        // Setze einen Click-Listener für den "Registrieren"-Button, um zum nächsten Onboarding-Fragment zu navigieren
        binding.btRegistrate.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingOneFragment_to_onBoardingTwoFragment)
        }
    }
}