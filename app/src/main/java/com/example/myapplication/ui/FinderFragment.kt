package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
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

    private lateinit var binding: FinderFragmentBinding
    private val viewModel: ViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()
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
        binding.ddBtSort.isClickable = false

        binding.ddbtSearch.setOnClickListener {
            showPopupMenuSearch(it)
        }



        binding.btSearch.setOnClickListener {
            if (binding.ddbtSearch.text == "MATCHES") {
                viewModel.contacts.observe(viewLifecycleOwner) { originalList ->
                    Log.d("FinderFragment", "Original list size: ${originalList.size}")

                    val filteredList = viewModel.filterAndSort(
                        ViewModel.Level.valueOf(binding.ddbtLvl.text.toString()),
                        ViewModel.Sports.valueOf(binding.ddBtSports.text.toString()),
                        binding.ddBtSort.text.toString(),
                        originalList.toMutableList(),
                    )

                    Log.d("FinderFragment", "Filtered list size: ${filteredList.size}")

                    // Aktualisieren Sie den Adapter mit der gefilterten Liste
                    binding.rvFinderResults.adapter = FinderResultAdapter(filteredList, viewModel)
                }
            }
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

                R.id.option9 -> {
                    binding.ddBtSports.text = "SPORTS"
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
                    binding.ddBtSort.text = "GRÖSSE"
                    true
                }

                R.id.option5 -> {
                    binding.ddBtSort.text = "MATCHES"
                    true
                }

                R.id.option6 -> {
                    binding.ddBtSort.text = "WINS"
                    true
                }

                R.id.option7 -> {
                    binding.ddBtSort.text = "POKALE"
                    true
                }

                R.id.option8 -> {
                    binding.ddBtSort.text = "SORT"
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }

    private fun showPopupMenuSortClubs(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_sort_clubs, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    binding.ddBtSort.text = "ENTFERNUNG"
                    true
                }

                R.id.option2 -> {
                    binding.ddBtSort.text = "EST"
                    true
                }

                R.id.option3 -> {
                    binding.ddBtSort.text = "QUOTE"
                    true
                }

                R.id.option4 -> {
                    binding.ddBtSort.text = "POKALE"
                    true
                }

                R.id.option5 -> {
                    binding.ddBtSort.text = "TUNIERE"
                    true
                }

                R.id.option6 -> {
                    binding.ddBtSort.text = "LIGEN"
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
                    binding.ddbtLvl.text = "EXPERT"
                    true
                }

                R.id.option5 -> {
                    binding.ddbtLvl.text = "ELITE"
                    true
                }

                R.id.option6 -> {
                    binding.ddbtLvl.text = "LEVEL"
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
                    binding.ddBtSort.text = "SORT"
                    binding.ddBtSort.isClickable = true
                    binding.ddBtSort.setOnClickListener {
                        showPopupMenuSort(it)
                    }
                    binding.btSearch.setOnClickListener {
                        // Verwenden Sie den vorher initialisierten Adapter
                        viewModel.contacts.observe(viewLifecycleOwner) { originalList ->
                            Log.d("FinderFragment", "Original list size: ${originalList.size}")
                            val filteredList = viewModel.filterAndSort(
                                ViewModel.Level.valueOf(
                                    if (binding.ddbtLvl.text.toString() == "LEVEL") {
                                        "ALLE"
                                    } else {
                                        binding.ddbtLvl.text.toString()
                                    }
                                ),
                                ViewModel.Sports.valueOf(
                                    if (binding.ddBtSports.text.toString() == "SPORTS") {
                                        "ALLE"
                                    } else {
                                        binding.ddBtSports.text.toString()
                                    }
                                ),
                                binding.ddBtSort.text.toString(),
                                originalList.toMutableList(),
                            )

                            Log.d("FinderFragment", "Filtered list size: ${filteredList.size}")

                            binding.rvFinderResults.adapter =
                                FinderResultAdapter(filteredList, viewModel)
                        }
                    }
                    true
                }

                R.id.option2 -> {
                    binding.ddbtSearch.text = "Clubs"
                    binding.ddbtLvl.visibility = View.GONE // Hide ddbtLvl
                    binding.ddBtSort.isClickable = true
                    binding.ddBtSort.setOnClickListener {
                        showPopupMenuSortClubs(it)
                    }
                    binding.btSearch.setOnClickListener {
                        // Verwenden Sie den vorher initialisierten Adapter
                        viewModel.clubdatabase.observe(viewLifecycleOwner) {
                            Log.d("FinderFragment", "Original list size: ${it.size}")
                            val filteredList = viewModel.filterAndSortClubs(
                                ViewModel.Sports.valueOf(binding.ddBtSports.text.toString()),
                                binding.ddBtSort.text.toString(),
                                it.toMutableList(),
                            )

                            Log.d("FinderFragment", "Filtered list size: ${filteredList.size}")

                            binding.rvFinderResults.adapter = ClubAdapter(filteredList, viewModel)

                        }
                    }
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }


}
