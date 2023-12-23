package com.example.myapplication.ui


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.RegistrateFragmentBinding

class RegistrateFragment : Fragment() {

    private lateinit var binding : RegistrateFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrateFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        binding.btRegistrate.setOnClickListener {
            var email = binding.tietEmail.text.toString()
            var passwort = binding.tierPassword.text.toString()

            if (binding.tietEmail.text!!.isEmpty() && binding.tierPassword.text!!.isEmpty()) {
                binding.textInputLayout4.visibility = INVISIBLE
                showPopUp("Daten fehlen", "Bitte geben Sie eine gültige Email-Adresse und ein Passwort mit mindestens 6 Zeichen ein")
                binding.btRegistrate.isClickable = false
            } else if (binding.tietEmail.text!!.isNotEmpty() && binding.tierPassword.text!!.isEmpty()) {
                binding.textInputLayout4.visibility = INVISIBLE
                showPopUp("Daten fehlen", "Bitte geben Sie zum Registrieren ein Passwort mit mindestens 6 Zeichen ein")
                binding.btRegistrate.isClickable = false
            } else if (binding.tietEmail.text!!.isEmpty() && binding.tierPassword.text!!.isNotEmpty()) {
                binding.textInputLayout4.visibility = INVISIBLE
                showPopUp("Daten fehlen", "Bitte geben Sie zum Registrieren eine gültige Email-Adresse ein")
                binding.btRegistrate.isClickable = false
            } else {
                firebaseViewModel.register(email, passwort)
            }
        }
    }
    private fun showPopUp(titel: String, nachricht: String) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setTitle(titel)
        builder.setMessage(nachricht)
        builder.setPositiveButton("OK") { dialog, which ->

            binding.btRegistrate.isClickable = true
            dialog.dismiss()
            binding.textInputLayout4.visibility = View.VISIBLE
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}