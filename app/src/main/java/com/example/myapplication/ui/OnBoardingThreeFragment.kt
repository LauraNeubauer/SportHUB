package com.example.myapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.OnboardingThreeFragmentBinding

class OnBoardingThreeFragment : Fragment() {

    private lateinit var binding: OnboardingThreeFragmentBinding
    private var Verified = false
    private var selectedLevel: String? = null
    private val checkedItems = booleanArrayOf(false, false, false, false)
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()


    private var email: String? = null
    private var pw: String? = null
    private var name: String? = null
    private var age: String? = null
    private var size: String? = null
    private var bio: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnboardingThreeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }

        binding.ddBtSortCal.setOnClickListener {
            showMultiSelectionDialog(it)
        }
        binding.ddBtSortCal2.setOnClickListener {
            showPopupMenuLevel(it)
        }

        binding.btWeiter.setOnClickListener {
            if (
                Verified &&
                binding.tietBio.text!!.isNotEmpty() &&
                checkedItems.contains(true) &&
                binding.tietName.text!!.isNotEmpty() &&
                binding.tietAge.text!!.isNotEmpty() &&
                binding.tietSize.text!!.isNotEmpty() &&
                selectedLevel != null
            ) {

                val receivedArguments = arguments

                email = receivedArguments!!.getString("email")
                pw = receivedArguments.getString("pw")
                name = binding.tietName.text!!.toString()
                age = binding.tietAge.text!!.toString()
                size = binding.tietSize.text!!.toString()
                bio = binding.tietBio.text!!.toString()

                val selectedSportsString = getSelectedSportsAsString()

                val bundle = Bundle()

                bundle.putString("email", email)
                bundle.putString("pw", pw)
                bundle.putString("name", name)
                bundle.putString("age", age)
                bundle.putString("size", size)
                bundle.putString("bio", bio)
                bundle.putString("level", selectedLevel)
                bundle.putString("selectedSports", selectedSportsString)
                bundle.putString("gender", if (binding.switch1.isChecked) "female" else "male")


                findNavController().navigate(
                    R.id.action_onBoardingThreeFragment_to_onBoardingFourFragment,
                    bundle
                )
            } else if (
                Verified &&
                (binding.tietBio.text!!.isEmpty() ||
                !checkedItems.contains(true) ||
                binding.tietName.text!!.isEmpty() ||
                binding.tietAge.text!!.isEmpty() ||
                binding.tietSize.text!!.isEmpty() ||
                selectedLevel == null)
            ) {
                Verified = false
                showPopUp(
                    "Fehlende Daten",
                    "Bitte füllen sie alle Felder aus und wählen Sie Ihr Sport-Level und Sportarten aus!"
                )
            } else if (
                !Verified &&
                (binding.tietBio.text!!.isEmpty() ||
                !checkedItems.contains(true) ||
                binding.tietName.text!!.isEmpty() ||
                binding.tietAge.text!!.isEmpty() ||
                binding.tietSize.text!!.isEmpty() ||
                selectedLevel == null)
            ) {
                Verified = false
                showPopUp(
                    "Fehlende Daten",
                    "Bitte füllen sie alle Felder aus und wählen Sie Ihr Sport-Level und Sportarten aus!"
                )
            } else if (
                !Verified &&
                binding.tietBio.text!!.isNotEmpty() &&
                checkedItems.contains(true) &&
                binding.tietName.text!!.isNotEmpty() &&
                binding.tietAge.text!!.isNotEmpty() &&
                binding.tietSize.text!!.isNotEmpty() &&
                selectedLevel != null
            ) {
                showPopUpVerifier(
                    "Überprüfen", "Bitte überprüfen Sie ob alle Daten stimmen!"
                )
            }
        }
    }

    private fun showMultiSelectionDialog(view: View) {
        val sportsArray = arrayOf("BADMINTON", "SQUASH", "TISCHTENNIS", "TENNIS")
        val bundle = Bundle()

        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialogStyle)
        builder.setTitle("Wähle Sportarten")
            .setMultiChoiceItems(sportsArray, checkedItems) { _, which, isChecked ->
                checkedItems[which] = isChecked
            }
            .setPositiveButton("OK") { _, _ ->
                val selectedCount = checkedItems.count { it }
                binding.ddBtSortCal.text = "$selectedCount Sportarten ausgewählt"
                val selectedSports = sportsArray.filterIndexed { index, _ ->
                    checkedItems[index]
                }
                selectedSports.forEachIndexed { index, sport ->
                    bundle.putString("sport${index + 1}", sport)
                }
            }
            .setNegativeButton("Abbrechen") { _, _ -> }
            .show()
    }

    private fun showPopupMenuLevel(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_level, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    binding.ddBtSortCal2.text = "BEGINNER"
                    selectedLevel = "BEGINNER"
                    true
                }

                R.id.option2 -> {
                    binding.ddBtSortCal2.text = "IMPROVER"
                    selectedLevel = "IMPROVER"
                    true
                }

                R.id.option3 -> {
                    binding.ddBtSortCal2.text = "ADVANCED"
                    selectedLevel = "ADVANCED"
                    true
                }

                R.id.option4 -> {
                    binding.ddBtSortCal2.text = "PRACTICER"
                    selectedLevel = "PRACTICER"
                    true
                }

                R.id.option5 -> {
                    binding.ddBtSortCal2.text = "EXPERT"
                    selectedLevel = "EXPERT"
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }


    private fun showPopUp(titel: String, nachricht: String) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setTitle(titel)
        builder.setMessage(nachricht)
        builder.setPositiveButton("OK") { dialog, which ->
            binding.btWeiter.isClickable = true
            dialog.dismiss()
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showPopUpVerifier(titel: String, nachricht: String) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setTitle(titel)
        builder.setMessage(nachricht)
        builder.setPositiveButton("OK") { dialog, which ->
            binding.btWeiter.isClickable = true
            Verified = true
            dialog.dismiss()
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun getSelectedSportsAsString(): String {
        val sportsArray = arrayOf("BADMINTON", "SQUASH", "TISCHTENNIS", "TENNIS")
        val selectedSports = sportsArray.filterIndexed { index, _ ->
            checkedItems[index]
        }
        return selectedSports.joinToString(", ")
    }

}