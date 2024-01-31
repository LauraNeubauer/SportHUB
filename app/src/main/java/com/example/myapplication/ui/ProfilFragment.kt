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
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.data.ClubDatabase
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.ProfilFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel

class ProfilFragment : Fragment() {

    // die benötigten Variablen für das Binding und des ViewModels
    lateinit var binding: ProfilFragmentBinding
    private val firebaseVM : FirebaseViewModel by activityViewModels()

    // Aktivitätsresultat, um Bild aus der Galerie auszuwählen
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            firebaseVM.uploadImage(uri)
        }
    }

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfilFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Überwache Änderungen im Firebase-Datensatz des Benutzerprofils
        firebaseVM.profileRef.addSnapshotListener { value, error ->
            if (error == null && value != null) {
                // Konvertiere die Daten des Profils in ein PersonData-Objekt
                val myProfile = value.toObject(PersonData::class.java)

                // Aktualisiere die UI mit den Profilinformationen
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

                // Überprüfe, ob der Benutzer Matches hat, und aktualisiere die UI entsprechend
                if (myProfile.matches != "0") {
                    binding.cvMatch.visibility = View.VISIBLE
                    binding.tvLeague.text = ExampleDatabase().eventList.random().name
                    binding.tvSport.text = myProfile.sportsOne
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