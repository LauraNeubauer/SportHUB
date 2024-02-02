package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //In meiner Freizeit bin ich sehr gerne in Museen und bei Poetry Slams oder gehe zum Sport wenn ich nicht grade arbeite.

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setzt die Farbe der Statusleiste
        window.statusBarColor = ContextCompat.getColor(this, R.color.tags)

        // Initialisierung des Binding-Objekts
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verknüpft das BottomNavigationView mit dem NavController des NavHostFragments
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bnv.setupWithNavController(navHost.navController)

        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnv.visibility = when (destination.id) {
                // Blendet das BottomNavigationView für bestimmte Ziele aus
                R.id.chatDetailFragment,
                R.id.onBoardingThreeFragment,
                R.id.onBoardingFourFragment,
                R.id.onBoardingOneFragment,
                R.id.registrateFragment,
                R.id.onBoardingTwoFragment,
                R.id.resultsFragment -> View.GONE

                // Zeigt das BottomNavigationView für andere Ziele an
                else -> View.VISIBLE
            }
        }
    }
}