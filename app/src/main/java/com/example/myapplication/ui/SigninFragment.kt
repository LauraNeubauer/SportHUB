package com.example.myapplication.ui


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.SigninFragmentBinding

class SigninFragment : Fragment() {

    private lateinit var binding : SigninFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SigninFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        binding.btSignUp.setOnClickListener {
            if (binding.tietEmail.text!!.isNotEmpty() && binding.tietPw.text!!.isNotEmpty()) {
                var mail = binding.tietEmail.text!!.toString()
                var email = binding.tietPw.text!!.toString()
                firebaseViewModel.login(mail, email)
            } else {
                showPopUp("Daten fehlen", "Bitte geben Sie die Email-Adresse und das Passwort ein, mit dem Sie sich registriert haben!")
            }
        }
    }

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