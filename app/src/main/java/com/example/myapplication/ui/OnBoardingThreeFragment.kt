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
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()
    private var bioVerified = false


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

        var name = binding.tietName.text
        var age = binding.tietAge.text
        var size = binding.tietSize.text
        var bio = binding.tietBio.text

        binding.ddBtSortCal.setOnClickListener {
            showMultiSelectionDialog(it)
        }
        binding.ddBtSortCal2.setOnClickListener {
            showPopupMenuLevel(it)
        }
        binding.btWeiter.setOnClickListener {
            val selectedSports = binding.ddBtSortCal.text.toString()
            val selectedLevel = binding.ddBtSortCal2.text.toString()

            if (bioVerified &&
                bio!!.isNotEmpty() &&
                binding.tietAge.text!!.isNotEmpty() &&
                binding.tietName.text!!.isNotEmpty() &&
                binding.tietSize.text!!.isNotEmpty() &&
                selectedSports.isNotEmpty() &&
                selectedLevel.isNotEmpty()
            ) {
                findNavController().navigate(R.id.action_onBoardingThreeFragment_to_onBoardingFourFragment)
            } else if (
                !bioVerified &&
                (bio!!.isEmpty() ||
                binding.tietAge.text!!.isEmpty() ||
                binding.tietName.text!!.isEmpty() ||
                binding.tietSize.text!!.isEmpty() ||
                selectedSports.isEmpty() ||
                selectedLevel.isEmpty())){
                binding.tvBio.text = bio
                binding.tietBio.text!!.clear()
                showPopUp("Fehlende Daten", "Bitte füllen sie alle Felder aus und wählen Sie Ihr Sport-Level und Sportarten aus!")
            }
        }
    }

    private fun showMultiSelectionDialog(view: View) {
        val sportsArray = arrayOf("BADMINTON", "SQUASH", "TISCHTENNIS", "TENNIS")
        val checkedItems = booleanArrayOf(false, false, false, false)

        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialogStyle)
        builder.setTitle("Wähle Sportarten")
            .setMultiChoiceItems(sportsArray, checkedItems) { _, which, isChecked ->
                checkedItems[which] = isChecked
            }
            .setPositiveButton("OK") { _, _ ->
                val selectedCount = checkedItems.count { it }
                binding.ddBtSortCal.text = "$selectedCount Sportarten ausgewählt"
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
                    true
                }
                R.id.option2 -> {
                    binding.ddBtSortCal2.text = "IMPROVER"
                    true
                }
                R.id.option3 -> {
                    binding.ddBtSortCal2.text = "ADVANCED"
                    true
                }
                R.id.option4 -> {
                    binding.ddBtSortCal2.text = "PRACTICER"
                    true
                }
                R.id.option5 -> {
                    binding.ddBtSortCal2.text = "EXPERT"
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
            bioVerified = true
            dialog.dismiss()
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}