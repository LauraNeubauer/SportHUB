package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bnv.setupWithNavController(navHost.navController)

        navHost.navController.addOnDestinationChangedListener{_,destination,_ ->
            when (destination.id) {
               R.id.onBoardingOneFragment -> binding.bnv.visibility = View.GONE
               R.id.onBoardingTwoFragment -> binding.bnv.visibility = View.GONE
                else -> binding.bnv.visibility = View.VISIBLE

            }
        }
    }
}