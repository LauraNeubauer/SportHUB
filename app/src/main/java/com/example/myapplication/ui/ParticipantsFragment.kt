package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.databinding.ParticipantsFragmentBinding

class ParticipantsFragment : Fragment() {


    private lateinit var binding: ParticipantsFragmentBinding
    private val viewModel : ViewModel by activityViewModels()
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ParticipantsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}