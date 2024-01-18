package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.StrangerClubFragmentBinding

class StrangerClubFragment : Fragment() {

    private lateinit var binding: StrangerClubFragmentBinding
    private val vielmodel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StrangerClubFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vielmodel.getCurrentClub.observe(viewLifecycleOwner){

            binding.tvClubName.text = it.name
            binding.btParticipants.text = it.participants.toString()
            binding.btSportsOne.text = it.sport
            binding.tvBio.text = it.bio
            binding.tvGroupOne.text = it.name

            binding.tvStatEst.text = it.est.toString()
            binding.tvStatsLigen.text = it.ligen.toString()
            binding.tvStatsPokale.text = it.pokale.toString()
            binding.tvStatsQuote.text = it.Quote.toString()
            binding.tvStatsTuniere.text = it.tuniere.toString()

        }

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.finderFragment)
        }

    }
}