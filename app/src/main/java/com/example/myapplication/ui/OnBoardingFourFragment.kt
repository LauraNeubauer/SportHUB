package com.example.myapplication.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.R
import com.example.myapplication.databinding.OnboardingFourFragmentBinding

class OnBoardingFourFragment : Fragment() {

    private lateinit var binding: OnboardingFourFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()
    private val personViewModel : PersonViewModel by activityViewModels()

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

        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        val receivedArguments = arguments

        val email = receivedArguments!!.getString("email")
        val pw = receivedArguments.getString("pw")
        val name = receivedArguments.getString("name")
        val age = receivedArguments.getString("age")
        val size = receivedArguments.getString("size")
        val bio = receivedArguments.getString("bio")
        val level = receivedArguments.getString("level")
        val sports = receivedArguments.getString("selectedSports")
        val gender = receivedArguments.getString("gender")

        val sportsList = sports?.split(", ")
        val sportsOne = sportsList?.getOrNull(0)
        val sportsTwo = sportsList?.getOrNull(1)

        binding.tvName.text = name
        binding.tvStatAge.text = age
        binding.tvStatSize.text = size
        binding.tvBio.text = bio
        binding.level.text = level
        binding.btSportsOne.text = sportsOne
        binding.btSportsTwo.text = sportsTwo

        binding.btPicturePacker.setOnClickListener {
            openGallery()
        }

        val personData = PersonData(
            name = name!!,
            gender = gender,
            age = age!!,
            pic = binding.ivProfilpicture.toString(),
            trophys = "0",
            matches = "0",
            wins = "0",
            size = size!!,
            level = level!!,
            sportsOne = sportsOne!!,
            sportsTwo = sportsTwo!!,
            bio = bio!!,
        )

        personViewModel.insertPerson(personData)
        firebaseViewModel.register(email!!, pw!!)

        binding.btWeiter.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri = data?.data
                binding.ivProfilpicture.setImageURI(selectedImageUri)
            }
        }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
}