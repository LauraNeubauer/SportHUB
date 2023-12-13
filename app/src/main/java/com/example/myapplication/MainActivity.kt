package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.tags)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.bnv.setupWithNavController(navHost.navController)

    /*    navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> binding.bnv.visibility = View.GONE
                R.id.profilFragment -> binding.bnv.visibility = View.GONE
                else -> binding.bnv.visibility = View.VISIBLE
            }
        }  */

    }
}