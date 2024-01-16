package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.Firebase.FirebaseViewModel
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.R
import com.example.myapplication.adapter.ClubAdapter
import com.example.myapplication.adapter.FinderResultAdapter
import com.example.myapplication.databinding.FinderFragmentBinding

class FinderFragment : Fragment() {

    private lateinit var binding : FinderFragmentBinding
    private val viewModel : ViewModel by activityViewModels()
    private val firebaseViewModel : FirebaseViewModel by activityViewModels()
    var myAge = ""
    var myWins = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FinderFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseViewModel.profileRef.addSnapshotListener { value, error ->
            if (error == null && value != null) {
                // Umwandeln des Snapshots in eine Klassen-Instanz von der Klasse Profil und setzen der Felder
                val myProfile = value.toObject(PersonData::class.java)
                myAge = myProfile!!.age
                myWins = myProfile.wins
            }
        }

        binding.ddBtSports.text = "SPORTS"
        binding.ddBtSports.setOnClickListener {
            showPopupMenuSports(it)
        }

        binding.ddbtLvl.text = "LEVEL"
        binding.ddbtLvl.setOnClickListener {
            showPopupMenuLevel(it)
        }

        binding.ddBtSort.text = "SORT"
        binding.ddBtSort.setOnClickListener {
            showPopupMenuSort(it)
        }

        binding.ddbtSearch.text = "SEARCH"
        binding.ddbtSearch.setOnClickListener {
            showPopupMenuSearch(it)
        }

        binding.ddbtSearch.setOnClickListener {
            showPopupMenuSearch(it)
        }
    }

    private fun showPopupMenuSports(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_sports, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    binding.ddBtSports.text = "BADMINTON"
                    true
                }
                R.id.option2 -> {
                    binding.ddBtSports.text = "SQUASH"
                    true
                }
                R.id.option3 -> {
                    binding.ddBtSports.text = "TISCHTENNIS"
                    true
                }
                R.id.option4 -> {
                    binding.ddBtSports.text = "TENNIS"
                    true
                }
                R.id.option5 -> {
                    binding.ddBtSports.text = "FUSSBALL"
                    true
                }
                R.id.option6 -> {
                    binding.ddBtSports.text = "HOCKEY"
                    true
                }
                R.id.option7 -> {
                    binding.ddBtSports.text = "CRICKET"
                    true
                }
                R.id.option8 -> {
                    binding.ddBtSports.text = "HANDBALL"
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }
    private fun showPopupMenuSort(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_sort, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    binding.ddBtSort.text = "ENTFERNUNG"
                    true
                }
                R.id.option2 -> {
                    binding.ddBtSort.text = "DATUM"
                    true
                }
                R.id.option3 -> {
                    binding.ddBtSort.text = "ALTER"
                    true
                }
                R.id.option4 -> {
                    binding.ddBtSort.text = "WINS"
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
    private fun showPopupMenuLevel(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_level, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    binding.ddbtLvl.text = "BEGINNER"
                    true
                }
                R.id.option2 -> {
                    binding.ddbtLvl.text = "IMPROVER"
                    true
                }
                R.id.option3 -> {
                    binding.ddbtLvl.text = "ADVANCED"
                    true
                }
                R.id.option4 -> {
                    binding.ddbtLvl.text = "PRACTICER"
                    true
                }
                R.id.option5 -> {
                    binding.ddbtLvl.text = "EXPERT"
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }

    private fun showPopupMenuSearch(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_search, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    binding.ddbtSearch.text = "Matches"
                    binding.ddbtLvl.visibility = View.VISIBLE // Show ddbtLvl
                    viewModel.contacts.observe(viewLifecycleOwner) { originalList ->
                        var filteredList = originalList // Starte mit der ursprünglichen Liste

                        filteredList = when (binding.ddbtLvl.text) {
                            "BEGINNER" -> filteredList.filter { it.level == "BEGINNER" }.toMutableList()
                            "IMPROVER" -> filteredList.filter { it.level == "IMPROVER" }.toMutableList()
                            "ADVANCED" -> filteredList.filter { it.level == "ADVANCED" }.toMutableList()
                            "PRACTITIONER" -> filteredList.filter { it.level == "PRACTITIONER" }.toMutableList()
                            "EXPERT" -> filteredList.filter { it.level == "EXPERT" }.toMutableList()
                            else -> filteredList
                        }

                        filteredList = when (binding.ddBtSports.text) {
                            "BADMINTON" -> filteredList.filter { it.sportsOne == "BADMINTON" }.toMutableList()
                            "SQUASH" -> filteredList.filter { it.sportsOne == "SQUASH" }.toMutableList()
                            "TISCHTENNIS" -> filteredList.filter { it.sportsOne == "TISCHTENNIS" }.toMutableList()
                            "TENNIS" -> filteredList.filter { it.sportsOne == "TENNIS" }.toMutableList()
                            "FUSSBALL" -> filteredList.filter { it.sportsOne == "FUSSBALL" }.toMutableList()
                            "HOCKEY" -> filteredList.filter { it.sportsOne == "HOCKEY" }.toMutableList()
                            "CRICKET" -> filteredList.filter { it.sportsOne == "CRICKET" }.toMutableList()
                            "HANDBALL" -> filteredList.filter { it.sportsOne == "HANDBALL" }.toMutableList()
                            else -> filteredList
                        }

                        filteredList = when (binding.ddBtSort.text) {
                            "ENTFERNUNG" -> filteredList.sortedBy { it.entfernung }.toMutableList()
                            "DATUM" -> filteredList.sortedBy { it.date }.toMutableList()
                            "WINS" -> filteredList.sortedBy { it.wins > myWins }.toMutableList()
                            "ALTER" -> filteredList.sortedBy { it.age > myAge }.toMutableList()
                            else -> filteredList
                        }

                        binding.btSearch.setOnClickListener {
                            binding.rvFinderResults.adapter = FinderResultAdapter(filteredList, viewModel)
                        }
                    }
                    true
                }
                R.id.option2 -> {
                    binding.ddbtSearch.text = "Clubs"
                    binding.ddbtLvl.visibility = View.GONE // Hide ddbtLvl
                    viewModel.clubdatabase.observe(viewLifecycleOwner) {
                        binding.rvFinderResults.adapter = ClubAdapter(it, viewModel)
                    }
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

}
