package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.myapplication.R
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.StrangerProfilFragmentBinding
import com.example.myapplication.viewmodel.MainViewModel

class StrangerProfilFragment : Fragment() {

    private lateinit var binding : StrangerProfilFragmentBinding
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StrangerProfilFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.currentProfile.observe(viewLifecycleOwner) {

            binding.ivProfilePicture.load(it.pic)
            binding.statOne.text = it.age
            binding.textStatOne.text = "ALTER"
            binding.statTwo.text = it.size
            binding.textStatTwo.text = "GRÖSSE"
            binding.statThree.text = it.matches
            binding.textStatThree.text = "MATCHES"
            binding.statFour.text = it.wins
            binding.textStatFour.text = "WINS"
            binding.statFive.text = it.trophys
            binding.textStatFive.text = "POKALE"
            binding.tvName.text = it.name

            binding.tvBio.text = it.bio

            binding.tvLevel.text = it.level
            binding.btSportsOne.text = it.sportsOne
            binding.btSportsTwo.text = it.sportsTwo
            binding.cvMatch.visibility = INVISIBLE


            binding.btBack.setOnClickListener {
                findNavController().navigate(R.id.finderFragment)
            }

            if (it.matches != "0") {
               binding.cvMatch.visibility = VISIBLE
                binding.tvLeague.text = ExampleDatabase().eventList.random().name
                binding.tvSport.text = it.sportsOne
                binding.tvTime.text = ""
                var clubs = ClubDatabase().getClubs()
                binding.tvGroupOne.text = clubs.value!![it.club!!].name
                binding.tvGroupTwo.text = clubs.value!!.random().name
                binding.tvGroupOneGoals.text = listOf<Int>(1,2,3,4,5,6,7,8).random().toString()
                binding.tvGroupTwoGoals.text = listOf<Int>(1,2,3,4,5,6,7,8).random().toString()
            }
        }
    }
}