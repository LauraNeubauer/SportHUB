package com.example.myapplication.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.PersonApi.PersonViewModel
import com.example.myapplication.R
import com.example.myapplication.adapter.EventHomeAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.CalenderFragmentBinding
import java.util.Calendar

class CalenderFragment : Fragment() {

    private lateinit var binding : CalenderFragmentBinding
    var datasetEvents = ExampleDatabase().loadEvents()
    private val viewModel : PersonViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CalenderFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEvents.adapter = EventHomeAdapter(datasetEvents, viewModel)

        binding.ddBtSortCal.text = "SORT"
        binding.ddBtSortCal.setOnClickListener {
            showPopupMenuSortCal(it)
        }

    }

    private fun showPopupMenuSortCal(view: View) {
        val popupMenu = PopupMenu(requireContext(), view, 0, 2, R.style.PopupMenu)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.dd_menu_sort_cal, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.option1 -> {
                    binding.ddBtSortCal.text = "DIESE WOCHE"
                    true
                }
                R.id.option2 -> {
                    binding.ddBtSortCal.text = "NÄCHSTE WOCHE"
                    true
                }
                R.id.option3 -> {
                    binding.ddBtSortCal.text = "IN 2 WOCHEN"
                    true
                }
                R.id.option4 -> {
                    binding.ddBtSortCal.text = "IN 3 WOCHEN"
                    true
                }
                R.id.option5 -> {
                    binding.ddBtSortCal.text = "NÄCHSTEN MONAT"
                    true
                }
                R.id.option6 -> {
                    showDatePickerDialog()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.DatePickerDialogTheme, // Hier wird der benutzerdefinierte Stil angewendet
            DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Hier wird das ausgewählte Datum verarbeitet
                val selectedDate = "$dayOfMonth.${monthOfYear + 1}.$year"
                binding.ddBtSortCal.text = selectedDate
            },
            year,
            month,
            day
        )

        // DatePicker-Dialog anzeigen
        datePickerDialog.show()
    }
}