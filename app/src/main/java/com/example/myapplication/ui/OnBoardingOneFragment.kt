package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.databinding.OnboardingOneFragmentBinding

class OnBoardingOneFragment : Fragment() {

    private lateinit var binding : OnboardingOneFragmentBinding
    private val viewModel : PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnboardingOneFragmentBinding.inflate(layoutInflater)
        viewModel.loadPersons()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button4.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingOneFragment_to_onBoardingTwoFragment)
        }

    }
}