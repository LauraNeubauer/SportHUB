package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.databinding.OnboardingFourFragmentBinding

class OnBoardingFourFragment : Fragment() {

    private lateinit var binding: OnboardingFourFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnboardingFourFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receivedArguments = arguments

        val email = receivedArguments!!.getString("email")
        val pw = receivedArguments.getString("pw")
        val name = receivedArguments.getString("name")
        val age = receivedArguments.getString("age")
        val size = receivedArguments.getString("size")
        val bio = receivedArguments.getString("bio")
        val level = receivedArguments.getString("level")

        binding.tvName.text = name
        binding.tvStatAge.text = age
        binding.tvStatSize.text = size
        binding.tvBio.text = bio
        binding.level.text = level

    }
}