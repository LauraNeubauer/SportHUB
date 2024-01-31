package com.example.myapplication.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.R
import com.example.myapplication.databinding.OnboardingFourFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel
import com.example.myapplication.viewmodel.MainViewModel
import java.util.Calendar

// Globale Variable zur Speicherung der ausgewählten Bild-URI
var selectedImageUri : Uri? = null

class OnBoardingFourFragment : Fragment() {

    //die benötigten Variablen für das Binding und die ViewModels
    private lateinit var binding: OnboardingFourFragmentBinding
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()
    private val personMainViewModel : MainViewModel by activityViewModels()

    // Wird aufgerufen, um das Fragment zu erstellen und die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflatiere das Layout für dieses Fragment
        binding = OnboardingFourFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Erhalte die Argumente, die beim Navigieren zu diesem Fragment übergeben wurden
        val receivedArguments = arguments

        // Extrahiere die benötigten Informationen aus den Argumenten
        val email = receivedArguments!!.getString("email")
        val pw = receivedArguments.getString("pw")
        val name = receivedArguments.getString("name")!!
        val age = receivedArguments.getString("age")
        val size = receivedArguments.getString("size")
        val bio = receivedArguments.getString("bio")
        val level = receivedArguments.getString("level")
        val sportsOne = receivedArguments.getString("sportOne")
        val sportsTwo = receivedArguments.getString("sportTwo")
        val gender = receivedArguments.getString("gender")

        // Setze den Benutzernamen im FirebaseViewModel
        firebaseViewModel.setName(name)

        // Speichert die Benutzerinformationen auf der Benutzeroberfläche
        binding.tvName.text = name
        binding.tvStatAge.text = age
        binding.tvStatSize.text = size
        binding.tvBio.text = bio
        binding.level.text = level
        binding.btSportsOne.text = sportsOne
        binding.btSportsTwo.text = sportsTwo

        // Setze einen Click-Listener für den Button "Picture Picker", um die Galerie zu öffnen
        binding.btPicturePacker.setOnClickListener {
            openGallery()
        }

        // Erstelle ein aktuelles Datum
        val currentDate = Calendar.getInstance().time
        val calendar = Calendar.getInstance()
        calendar.time = currentDate

        // Erstelle ein PersonData-Objekt mit den Benutzerinformationen
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
            bio = bio!!,
            date = currentDate.toString()
        )

        // Setze einen Click-Listener für den Button "Weiter", um zur HomeFragment zu navigieren
        // und die Benutzerdaten zu speichern und das Bild hochzuladen
        binding.btWeiter.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
            personMainViewModel.insertPerson(personData)
            firebaseViewModel.uploadImage(selectedImageUri!!)
        }
    }

    // Activity Result Launcher für die Galerieauswahl
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                // Erhalte die ausgewählte Bild-URI und zeige das Bild auf der Benutzeroberfläche an
                selectedImageUri = data?.data
                binding.ivProfilpicture.setImageURI(selectedImageUri)
            }
        }

    // Funktion zum Öffnen der Galerie
    fun openGallery() {
        // Starte die Galerieauswahl über den Activity Result Launcher
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
}