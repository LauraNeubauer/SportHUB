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
import android.widget.TextView
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

        viewModel.currentImageIndex.observe(viewLifecycleOwner) { index ->
            binding.ads.setImageResource(viewModel.imageList[index])
        }

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
        val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        fun setBindingText(binding: TextView, day: Int) {
            val wrappedDay = (day + lastDayOfMonth) % lastDayOfMonth // Ensure day is within 1 to lastDayOfMonth
            binding.text = if (wrappedDay == 0) lastDayOfMonth.toString() else wrappedDay.toString()
        }
        when (SimpleDateFormat("EEEE").format(calendar.time)) {
            "Monday" -> {
                setBindingText(binding.dateOne, dayOfMonth)
                setBindingText(binding.dateTwo, dayOfMonth + 1)
                setBindingText(binding.dateThree, dayOfMonth + 2)
                setBindingText(binding.dateFour, dayOfMonth + 3)
                setBindingText(binding.dateFive, dayOfMonth + 4)
                setBindingText(binding.dateSix, dayOfMonth + 5)
                setBindingText(binding.dateSeven, dayOfMonth + 6)
            }

            "Tuesday" -> {
                setBindingText(binding.dateOne, dayOfMonth - 1)
                setBindingText(binding.dateTwo, dayOfMonth)
                setBindingText(binding.dateThree, dayOfMonth + 1)
                setBindingText(binding.dateFour, dayOfMonth + 2)
                setBindingText(binding.dateFive, dayOfMonth + 3)
                setBindingText(binding.dateSix, dayOfMonth + 4)
                setBindingText(binding.dateSeven, dayOfMonth + 5)
            }

            "Wednesday" -> {
                setBindingText(binding.dateOne, dayOfMonth - 2)
                setBindingText(binding.dateTwo, dayOfMonth - 1)
                setBindingText(binding.dateThree, dayOfMonth)
                setBindingText(binding.dateFour, dayOfMonth + 1)
                setBindingText(binding.dateFive, dayOfMonth + 2)
                setBindingText(binding.dateSix, dayOfMonth + 3)
                setBindingText(binding.dateSeven, dayOfMonth + 4)
            }

            "Thursday" -> {
                setBindingText(binding.dateOne, dayOfMonth - 3)
                setBindingText(binding.dateTwo, dayOfMonth - 2)
                setBindingText(binding.dateThree, dayOfMonth - 1)
                setBindingText(binding.dateFour, dayOfMonth)
                setBindingText(binding.dateFive, dayOfMonth + 1)
                setBindingText(binding.dateSix, dayOfMonth + 2)
                setBindingText(binding.dateSeven, dayOfMonth + 3)
            }

            "Friday" -> {
                setBindingText(binding.dateOne, dayOfMonth - 4)
                setBindingText(binding.dateTwo, dayOfMonth - 3)
                setBindingText(binding.dateThree, dayOfMonth - 2)
                setBindingText(binding.dateFour, dayOfMonth - 1)
                setBindingText(binding.dateFive, dayOfMonth)
                setBindingText(binding.dateSix, dayOfMonth + 5)
                setBindingText(binding.dateSeven, dayOfMonth + 6)
            }

            "Saturday" -> {
                setBindingText(binding.dateOne, dayOfMonth - 5)
                setBindingText(binding.dateTwo, dayOfMonth - 4)
                setBindingText(binding.dateThree, dayOfMonth - 3)
                setBindingText(binding.dateFour, dayOfMonth - 2)
                setBindingText(binding.dateFive, dayOfMonth - 1)
                setBindingText(binding.dateSix, dayOfMonth)
                setBindingText(binding.dateSeven, dayOfMonth + 1)
            }

            "Sunday" -> {
                setBindingText(binding.dateOne, dayOfMonth - 6)
                setBindingText(binding.dateTwo, dayOfMonth - 5)
                setBindingText(binding.dateThree, dayOfMonth - 4)
                setBindingText(binding.dateFour, dayOfMonth - 3)
                setBindingText(binding.dateFive, dayOfMonth - 2)
                setBindingText(binding.dateSix, dayOfMonth - 1)
                setBindingText(binding.dateSeven, dayOfMonth)
            }

            else -> {
                // Handle other cases if needed
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