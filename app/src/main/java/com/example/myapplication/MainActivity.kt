package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.tags)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bnv.setupWithNavController(navHost.navController)

        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnv.visibility = when (destination.id) {
                R.id.chatDetailFragment,
                R.id.onBoardingThreeFragment,
                R.id.onBoardingFourFragment,
                R.id.onBoardingOneFragment,
                R.id.registrateFragment,
                R.id.onBoardingTwoFragment,
                R.id.resultsFragment -> View.GONE

                else -> View.VISIBLE
            }
        }
    }
}