package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Event

class ExampleDatabase {

    var eventList : List<Event> = listOf(
            Event("Holstein Cup", "Friedländer CLT", 6, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 3, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 2, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 3, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 2, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
            Event("Holstein Cup", "Friedländer CLT", 4, "fortgeschrittene", "11", "20.01.2024, 13:20 Uhr", "Elmshorner SportPark", R.drawable.crash_test_m, R.drawable.edgar_chaparro_shfo3woggtu_unsplash,R.drawable.jack_van_tricht_izwepu8aqbc_unsplash, R.drawable.karsten_winegeart_r6zarepldje_unsplash),
        )
        fun loadEvents() : List<Event>{
            return eventList
        }
}