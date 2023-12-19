package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.StrangerProfilFragmentBinding

class StrangerProfilFragment : Fragment() {

    private lateinit var binding : StrangerProfilFragmentBinding
    var datasetEvents = ExampleDatabase().loadEvents()
    var datasetNews = ExampleDatabase().loadNews()
    private val viewModel : PersonViewModel by activityViewModels()

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

        viewModel.currentProfile.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
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

            binding.tvLevel.text = it.level

            binding.btSportsOne.text = it.sportsOne
            if (binding.btSportsOne.text == "FUSSBALL") {
                binding.btSportsOne.width = 100
            }
            binding.btSportsTwo.text = it.sportsTwo
        }
    }
}