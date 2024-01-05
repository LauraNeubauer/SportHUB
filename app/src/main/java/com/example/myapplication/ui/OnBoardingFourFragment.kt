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
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.databinding.OnboardingFourFragmentBinding

class OnBoardingFourFragment : Fragment() {

    private lateinit var binding: OnboardingFourFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()
    private val SELECT_IMAGE = 1

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
        val sports = receivedArguments.getString("selectedSports")

        val sportsList = sports?.split(", ")

        binding.tvName.text = name
        binding.tvStatAge.text = age
        binding.tvStatSize.text = size
        binding.tvBio.text = bio
        binding.level.text = level
        binding.btSportsOne.text = sportsList?.getOrNull(0)
        binding.btSportsTwo.text = sportsList?.getOrNull(1)

        binding.btPicturePacker.setOnClickListener {
            openGallery()
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

    // ...

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
}