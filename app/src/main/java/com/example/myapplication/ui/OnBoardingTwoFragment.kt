package com.example.myapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.OnboardingTwoFragmentBinding
class OnBoardingTwoFragment : Fragment() {

    private lateinit var binding: OnboardingTwoFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnboardingTwoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        binding.btLogin.setOnClickListener {
            var email = binding.tietEmail.text.toString()
            var passwort = binding.tierPassword.text.toString()

            if (binding.tietEmail.text!!.isEmpty() && binding.tierPassword.text!!.isEmpty()) {
                binding.textInputLayout4.visibility = INVISIBLE
                showPopUp("LogIn - Daten fehlen", "Geben Sie die Email-Adresse und das Passwort ein, bei dem Sie sich bei SportHUB registriert haben")
                binding.btLogin.isClickable = false
            } else if (binding.tietEmail.text!!.isNotEmpty() && binding.tierPassword.text!!.isEmpty()) {
                binding.textInputLayout4.visibility = INVISIBLE
                showPopUp("LogIn - Daten fehlen", "Geben Sie das Passwort ein, bei dem Sie sich bei SportHUB registriert haben")
                binding.btLogin.isClickable = false
            } else if (binding.tietEmail.text!!.isEmpty() && binding.tierPassword.text!!.isNotEmpty()) {
                binding.textInputLayout4.visibility = INVISIBLE
                showPopUp("LogIn - Daten fehlen", "Geben Sie die Email-Adresse ein, bei dem Sie sich bei SportHUB registriert haben")
                binding.btLogin.isClickable = false
            } else {
                firebaseViewModel.login(email, passwort)
            }
        }

        binding.reset.setOnClickListener {
            var email = binding.tietEmail.text.toString()

            firebaseViewModel.sendPasswordReset(email)
        }
    }
    private fun showPopUp(titel: String, nachricht: String) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setTitle(titel)
        builder.setMessage(nachricht)
        builder.setPositiveButton("OK") { dialog, which ->

            binding.btLogin.isClickable = true
            dialog.dismiss()
            binding.textInputLayout4.visibility = VISIBLE
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
