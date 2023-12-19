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
import com.example.myapplication.PersonApi.local.PersonDatabase
import com.example.myapplication.R
import com.example.myapplication.adapter.FinderResultAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.FinderFragmentBinding
import com.example.myapplication.databinding.HomeFragmentBinding

class FinderFragment : Fragment() {

    private lateinit var binding : FinderFragmentBinding
    var datasetMatches = ExampleDatabase().loadMatches()
    var datasetEvents = ExampleDatabase().loadEvents()
    var datasetNews = ExampleDatabase().loadNews()
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

        binding.dropdownButton.setOnClickListener {
            showPopupMenu(it)
        }

        binding.rvFinderResults.adapter = FinderResultAdapter(datasetMatches)
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dropdown_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    // Aktion für Option 1
                    true
                }
                R.id.option2 -> {
                    // Aktion für Option 2
                    true
                }
                // Weitere Optionen können hier hinzugefügt werden

                else -> false
            }
        }

        popupMenu.show()
    }
}
