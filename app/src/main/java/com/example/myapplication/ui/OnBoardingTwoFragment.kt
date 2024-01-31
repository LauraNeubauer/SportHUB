package com.example.myapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.OnboardingTwoFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel

class OnBoardingTwoFragment : Fragment() {

    // die benötigten Variablen für das Binding und des ViewModels
    private lateinit var binding: OnboardingTwoFragmentBinding
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflatiere das Layout für dieses Fragment
        binding = OnboardingTwoFragmentBinding.inflate(layoutInflater)
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

        // Setze einen Click-Listener für den "Weiter"-Button
        binding.btWeiter.setOnClickListener {
            // Hole die eingegebenen Daten (Email, Passwort 1 und Passwort 2)
            var email = binding.tietEmail.text.toString()
            var pwOne = binding.tietPwOne.text.toString()
            var pwTwo = binding.tietPwTwo.text.toString()

            // Überprüfe verschiedene Szenarien und zeige entsprechende Hinweise an
            if (binding.tietEmail.text!!.isEmpty() && binding.tietPwOne.text!!.isEmpty() && binding.tietPwTwo.text!!.isEmpty()) {
                showPopUp(
                    "Daten fehlen", "Bitte geben Sie eine gültige Email-Adresse, ein Passwort mit mindestens 6 Zeichen und bestätigen Sie dieses!"
                )
                binding.btWeiter.isClickable = false
            } else if (binding.tietEmail.text!!.isNotEmpty() && binding.tietPwOne.text!!.isEmpty() && binding.tietPwTwo.text!!.isEmpty()) {
                showPopUp(
                    "Daten fehlen", "Bitte geben Sie zum Registrieren ein Passwort mit mindestens 6 Zeichen ein und bestätigen Sie dieses!"
                )
                binding.btWeiter.isClickable = false
            } else if (binding.tietEmail.text!!.isEmpty() && binding.tietPwOne.text!!.isNotEmpty() && binding.tietPwTwo.text!!.isNotEmpty()) {
                showPopUp(
                    "Daten fehlen", "Bitte geben Sie zum Registrieren eine gültige Email-Adresse ein!"
                )
                binding.btWeiter.isClickable = false
            } else if (binding.tietEmail.text!!.isNotEmpty() && binding.tietPwOne.text!!.isNotEmpty() && binding.tietPwTwo.text!!.isEmpty()) {
                showPopUp("Daten fehlen", "Bitte bestätigen Sie Ihr Passwort!")
                binding.btWeiter.isClickable = false
            } else {
                // Überprüfe, ob die Passwörter übereinstimmen
                if (pwOne == pwTwo) {
                    binding.btWeiter.isClickable = true
                    // Erstelle ein Bundle mit den eingegebenen Daten und navigiere zum nächsten Onboarding-Schritt
                    val bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("pw", pwOne)
                    findNavController().navigate(
                        R.id.action_onBoardingTwoFragment_to_onBoardingThreeFragment,
                        bundle
                    )
                } else {
                    showPopUp(
                        "Passwort stimmt nicht überein", "Ihr bestätigtes Passwort stimmt nicht überein - Bitte bestätigen Sie Ihr Passwort"
                    )
                }
            }
        }
    }

    // Funktion zum Anzeigen eines AlertDialog-Popups
    private fun showPopUp(titel: String, nachricht: String) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setTitle(titel)
        builder.setMessage(nachricht)
        builder.setPositiveButton("OK") { dialog, which ->

            binding.btWeiter.isClickable = true
            dialog.dismiss()
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
