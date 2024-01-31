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
import com.example.myapplication.databinding.SigninFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel

class SigninFragment : Fragment() {

    // die benötigten Variablen für das Binding und des ViewModels
    private lateinit var binding : SigninFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SigninFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Beobachte Änderungen am aktuellen Benutzer im FirebaseViewModel
        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        // Reagiere auf Klicks auf die Schaltfläche "Anmelden"
        binding.btSignUp.setOnClickListener {
            // Überprüfe, ob die E-Mail und das Passwort nicht leer sind
            if (binding.tietEmail.text!!.isNotEmpty() && binding.tietPw.text!!.isNotEmpty()) {
                // Extrahiere E-Mail und Passwort
                var mail = binding.tietEmail.text!!.toString()
                var email = binding.tietPw.text!!.toString()
                // Führe den Anmeldevorgang mit Firebase durch
                firebaseViewModel.login(mail, email)
            } else {
                // Zeige einen Pop-up-Fehler an, wenn E-Mail oder Passwort fehlen
                showPopUp("Daten fehlen", "Bitte geben Sie die Email-Adresse und das Passwort ein, mit dem Sie sich registriert haben!")
            }
        }
    }

    // Funktion zur Anzeige eines Pop-up-Fensters mit benutzerdefinierten Titel und Nachricht
    private fun showPopUp(titel: String, nachricht: String) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setTitle(titel)
        builder.setMessage(nachricht)
        builder.setPositiveButton("OK") { dialog, which ->
            binding.btSignUp.isClickable = true
            dialog.dismiss()
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}