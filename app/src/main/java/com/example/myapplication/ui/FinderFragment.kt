package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.R
import com.example.myapplication.adapter.FinderResultAdapter
import com.example.myapplication.databinding.FinderFragmentBinding

class FinderFragment : Fragment() {

    private lateinit var binding : FinderFragmentBinding
    private val viewModel : PersonViewModel by activityViewModels()

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

        viewModel.contacts.observe(viewLifecycleOwner) {
            binding.rvFinderResults.adapter = FinderResultAdapter(it, viewModel)
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
                    binding.ddBtSort.text = "ÜBEREINSTIMMUNG"
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
                    binding.ddbtLvl.visibility = View.VISIBLE // Zeige ddbtLvl an
                    true
                }
                R.id.option2 -> {
                    binding.ddbtSearch.text = "Clubs"
                    binding.ddbtLvl.visibility = View.GONE // Blende ddbtLvl aus
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}
