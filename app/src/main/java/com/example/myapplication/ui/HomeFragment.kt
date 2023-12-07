package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

   private lateinit var binding : HomeFragmentBinding

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = HomeFragmentBinding.inflate(layoutInflater)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding.person1.setImageResource(R.drawable.crash_test_m)
      binding.person2.setImageResource(R.drawable.crash_test_m)
      binding.person3.setImageResource(R.drawable.crash_test_m)
      binding.person4.setImageResource(R.drawable.crash_test_m)
      binding.person5.setImageResource(R.drawable.crash_test_m)
   }
}

