package com.example.myapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.R
import com.example.myapplication.adapter.ClubAdapter
import com.example.myapplication.adapter.FinderResultAdapter
import com.example.myapplication.databinding.FinderFragmentBinding
import com.example.myapplication.viewmodel.FirebaseViewModel
import com.example.myapplication.viewmodel.MainViewModel

// Klasse für das Fragment des Finders
class FinderFragment : Fragment() {

    //die benötigten Variablen für das Binding und die ViewModels
    private lateinit var binding: FinderFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val firebaseViewModel: FirebaseViewModel by activityViewModels()
    //Platzhalter-Initialisierungen für Alter und Wins
    var myAge = ""
    var myWins = ""

    // Wird aufgerufen, um die Ansichtshierarchie des Fragments zu erstellen und zurückzugeben
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflatiere das Layout für dieses Fragment
        binding = FinderFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    // Wird sofort nach onCreateView() aufgerufen und wird verwendet, um mit den Ansichten zu interagieren
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Beobachte Änderungen im aktuellen Bildindex im ViewModel und aktualisiere die Anzeige
        mainViewModel.currentImageIndex.observe(viewLifecycleOwner) { index ->
            binding.ads.setImageResource(mainViewModel.imageList[index])
        }

        // Überwache Änderungen am Profil-Snapshot in Firebase und aktualisiere die relevanten Daten
        firebaseViewModel.profileRef.addSnapshotListener { value, error ->
            if (error == null && value != null) {
                // Umwandeln des Snapshots in eine Klassen-Instanz von der Klasse Profil und setzen der Felder
                val myProfile = value.toObject(PersonData::class.java)
                myAge = myProfile!!.age
                myWins = myProfile.wins
            }
        }

        // Initialisiere Dropdown-Buttons und zeige Popup-Menüs für Sportarten, Level und Suche an
        binding.ddBtSports.text = "SPORTS"
        binding.ddBtSports.setOnClickListener {
            showPopupMenuSports(it)
        }

        binding.ddbtLvl.text = "LEVEL"
        binding.ddbtLvl.setOnClickListener {
            showPopupMenuLevel(it)
        }

        if (binding.ddbtSearch.text == "SEARCH") {
            binding.btSearch.setOnClickListener {
                showPopUp("Was möchten Sie suchen?", "Sie müssen zwischen Clubs oder \nMatches wählen. \n\nIm Suchfeld: SEARCH")
            }
        }

        binding.ddBtSort.text = "SORT"
        binding.ddBtSort.isClickable = false

        // Initialisiere das Popup für den Start des Fragments
        if (binding.ddbtSearch.text == "SEARCH") {
            showPopUpStart("FINDER", "Hier können Sie mittels Filter nach passenden Matches sowie nach Vereinen in der Datenbank suchen")
        }

        // Setze den Click-Listener für die Suche, abhängig von der ausgewählten Option (Matches oder Clubs)
        binding.ddbtSearch.setOnClickListener {
            showPopupMenuSearch(it)
        }
    }

    // Funktion zum Anzeigen des Popup-Menüs für Sportarten
    private fun showPopupMenuSports(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_sports, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                // Handling für verschiedene Sportarten
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

    // Funktion zum Anzeigen des Popup-Menüs für Sortieroptionen
    private fun showPopupMenuSort(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_sort, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    // Handling für verschiedene Sortieroptionen
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

    // Funktion zum Anzeigen des Popup-Menüs für Sortieroptionen von Clubs
    private fun showPopupMenuSortClubs(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_sort_clubs, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                // Handling für verschiedene Sortieroptionen von Clubs
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
                R.id.option7 -> {
                    binding.ddBtSort.text = "SORT"
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    // Funktion zum Anzeigen des Popup-Menüs für Level-Optionen
    private fun showPopupMenuLevel(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_level, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                // Handling für verschiedene Level-Optionen
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
                        mainViewModel.contacts.observe(viewLifecycleOwner) { originalList ->
                            Log.d("FinderFragment", "Original list size: ${originalList.size}")
                            val filteredList = mainViewModel.filterAndSort(
                                MainViewModel.Level.valueOf(
                                    if (binding.ddbtLvl.text.toString() == "LEVEL") {
                                        "ALLE"
                                    } else {
                                        binding.ddbtLvl.text.toString()
                                    }
                                ),
                                MainViewModel.Sports.valueOf(
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
                                FinderResultAdapter(filteredList, mainViewModel)
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
                        mainViewModel.clubdatabase.observe(viewLifecycleOwner) {
                            Log.d("FinderFragment", "Original list size: ${it.size}")
                            val filteredList = mainViewModel.filterAndSortClubs(
                                MainViewModel.Sports.valueOf(binding.ddBtSports.text.toString()),
                                binding.ddBtSort.text.toString(),
                                it.toMutableList(),
                            )

                            Log.d("FinderFragment", "Filtered list size: ${filteredList.size}")

                            binding.rvFinderResults.adapter = ClubAdapter(filteredList, mainViewModel)

                        }
                    }
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
            dialog.dismiss()
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        val window: Window? = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM // Setzen Sie die gewünschte Gravity

        val displayMetrics = resources.displayMetrics
        layoutParams?.height = (displayMetrics.heightPixels * 2) / 4
        layoutParams?.y = (displayMetrics.heightPixels - layoutParams!!.height) / 2
        window?.attributes = layoutParams

        dialog.show()
    }
    private fun showPopUpStart(titel: String, nachricht: String) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
        builder.setTitle(titel)
        builder.setMessage(nachricht)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        val window: Window? = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.gravity = Gravity.BOTTOM

        val displayMetrics = resources.displayMetrics
        layoutParams?.height = (displayMetrics.heightPixels * 2) / 4
        layoutParams?.y = (displayMetrics.heightPixels - layoutParams!!.height) / 2
        window?.attributes = layoutParams

        dialog.show()
    }

}
