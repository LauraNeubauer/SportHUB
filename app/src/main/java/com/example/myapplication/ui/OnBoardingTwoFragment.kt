package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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

        var email = binding.tietEmail.text.toString()
        var passwort = binding.tierPassword.text.toString()

        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        binding.btLogin.setOnClickListener {
            firebaseViewModel.login(email, passwort)
        }

        binding.reset.setOnClickListener {
            firebaseViewModel.sendPasswordReset(email)
        }
    }
}
