package com.example.myapplication.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ProfilFragmentBinding

class ProfilFragment : Fragment() {

    lateinit var binding: ProfilFragmentBinding
    private val viewModel : ViewModel by activityViewModels()
    private val firebaseVM : FirebaseViewModel by activityViewModels()

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            firebaseVM.uploadImage(uri)
        }
    }

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
                binding.cvMatch.visibility = View.INVISIBLE


                if (myProfile.matches != "0") {
                    binding.cvMatch.visibility = View.VISIBLE
                    binding.tvLeague.text = ExampleDatabase().eventList.random().name
                    binding.tvLand.text = myProfile.sportsOne
                    binding.tvTime.text = ""
                    var clubs = ClubDatabase().getClubs()
                    binding.tvGroupOne.text = clubs.value!![myProfile.club!!].name
                    binding.tvGroupTwo.text = clubs.value!!.random().name
                    binding.tvGroupOneGoals.text = listOf<Int>(1,2,3,4,5,6,7,8).random().toString()
                    binding.tvGroupTwoGoals.text = listOf<Int>(1,2,3,4,5,6,7,8).random().toString()
                }
            }
        }
    }
}