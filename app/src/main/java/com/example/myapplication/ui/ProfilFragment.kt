package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.databinding.ProfilFragmentBinding

class ProfilFragment : Fragment() {

    lateinit var binding: ProfilFragmentBinding
    private val viewModel : PersonViewModel by activityViewModels()
    private val firebaseVM : FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfilFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseVM.profileRef.addSnapshotListener { value, error ->
            if (error == null && value != null) {
                // Umwandeln des Snapshots in eine Klassen-Instanz von der Klasse Profil und setzen der Felder
                val myProfile = value.toObject(PersonData::class.java)
                binding.tvName.setText(myProfile!!.name)
                binding.tvLevel.setText(myProfile.level)
                binding.profilPic.load(myProfile.pic)
                binding.btSportsOne.setText(myProfile.sportsOne)
                binding.btSportsTwo.setText(myProfile.sportsTwo)

                binding.tvStatAge.setText(myProfile.age)
                binding.tvStatsMatches.setText(myProfile.matches)
                binding.tvStatsWins.setText(myProfile.wins)
                binding.tvStatsPokale.setText(myProfile.trophys)
                binding.tvAgeSize.setText(myProfile.size)

                binding.tvBio.setText(myProfile.bio)
            }
        }
    }
}