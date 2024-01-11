package com.example.myapplication.ui

import android.annotation.SuppressLint
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
import com.example.myapplication.PersonApi.ViewModel
import com.example.myapplication.R
import com.example.myapplication.adapter.EventHomeAdapter
import com.example.myapplication.data.ExampleDatabase
import com.example.myapplication.databinding.CalenderFragmentBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class CalenderFragment : Fragment() {

    private lateinit var binding: CalenderFragmentBinding
    private var datasetEvents = ExampleDatabase().loadEvents()
    private val viewModel: ViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CalenderFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEvents.adapter = EventHomeAdapter(datasetEvents, viewModel)

        binding.ddBtSortCal.text = "SORT"
        binding.ddBtSortCal.setOnClickListener {
            showPopupMenuSortCal(it)
        }
        weekdays()
    }

    @SuppressLint("SimpleDateFormat")
    private fun weekdays() {

        val currentDate = Calendar.getInstance().time
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        when (SimpleDateFormat("EEEE").format(calendar.time)) {
            "Monday" -> {
                binding.dateOne.text = dayOfMonth.toString()
                binding.dateTwo.text = (dayOfMonth.plus(1).toString())
                binding.dateThree.text = (dayOfMonth.plus(2).toString())
                binding.dateFour.text = (dayOfMonth.plus(3).toString())
                binding.dateFive.text = (dayOfMonth.plus(4).toString())
                binding.dateSix.text = (dayOfMonth.plus(5).toString())
                binding.dateSeven.text = (dayOfMonth.plus(6).toString())
            }
            "Tuesday" -> {
                binding.dateOne.text = (dayOfMonth - 1).toString()
                binding.dateTwo.text = dayOfMonth.toString()
                binding.dateThree.text = (dayOfMonth.plus(1).toString())
                binding.dateFour.text = (dayOfMonth.plus(2).toString())
                binding.dateFive.text = (dayOfMonth.plus(3).toString())
                binding.dateSix.text = (dayOfMonth.plus(4).toString())
                binding.dateSeven.text = (dayOfMonth.plus(5).toString())
            }
            "Wednesday" -> {
                binding.dateOne.text = (dayOfMonth - 2).toString()
                binding.dateTwo.text = (dayOfMonth - 1).toString()
                binding.dateThree.text = dayOfMonth.toString()
                binding.dateFour.text = (dayOfMonth.plus(1).toString())
                binding.dateFive.text = (dayOfMonth.plus(2).toString())
                binding.dateSix.text = (dayOfMonth.plus(3).toString())
                binding.dateSeven.text = (dayOfMonth.plus(4).toString())
            }
            "Thursday" -> {
                binding.dateOne.text = (dayOfMonth - 3).toString()
                binding.dateTwo.text = (dayOfMonth - 2).toString()
                binding.dateThree.text = (dayOfMonth - 1).toString()
                binding.dateFour.text = dayOfMonth.toString()
                binding.dateFive.text = (dayOfMonth.plus(1).toString())
                binding.dateSix.text = (dayOfMonth.plus(2).toString())
                binding.dateSeven.text = (dayOfMonth.plus(3).toString())
            }
            "Friday" -> {
                binding.dateOne.text = (dayOfMonth - 4).toString()
                binding.dateTwo.text = (dayOfMonth - 3).toString()
                binding.dateThree.text = (dayOfMonth - 2).toString()
                binding.dateFour.text = (dayOfMonth - 1).toString()
                binding.dateFive.text = dayOfMonth.toString()
                binding.dateSix.text = (dayOfMonth.plus(5).toString())
                binding.dateSeven.text = (dayOfMonth.plus(6).toString())
            }
            "Saturday" -> {
                binding.dateOne.text = (dayOfMonth - 5).toString()
                binding.dateTwo.text = (dayOfMonth - 4).toString()
                binding.dateThree.text = (dayOfMonth - 3).toString()
                binding.dateFour.text = (dayOfMonth - 2).toString()
                binding.dateFive.text = (dayOfMonth - 1).toString()
                binding.dateSix.text = dayOfMonth.toString()
                binding.dateSeven.text = (dayOfMonth.plus(1).toString())
            }
            "Sunday" -> {
                binding.dateThree.text = (dayOfMonth - 6).toString()
                binding.dateThree.text = (dayOfMonth - 5).toString()
                binding.dateThree.text = (dayOfMonth - 4).toString()
                binding.dateThree.text = (dayOfMonth - 3).toString()
                binding.dateThree.text = (dayOfMonth - 2).toString()
                binding.dateThree.text = (dayOfMonth - 1).toString()
                binding.dateOne.text = dayOfMonth.toString()
            }
            else -> {
            }
        }
    }

    @SuppressLint("SetTextI18n")
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
            R.style.DatePickerDialogTheme,
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = if (dayOfMonth < 10) {
                    "0$dayOfMonth.${monthOfYear + 1}.$year"
                } else {
                    "$dayOfMonth.${monthOfYear + 1}.$year"
                }
                binding.ddBtSortCal.text = selectedDate

            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}