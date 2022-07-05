package com.amk.stocktipsapp.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.bottomNavigationView
        val navController: NavController = findNavController(R.id.fragment_container_view)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.go_to_home,
                R.id.go_to_favorite,
                R.id.go_to_settings,
                R.id.company
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.go_to_home -> showBottomNav()
                R.id.go_to_favorite -> showBottomNav()
                R.id.go_to_settings -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }
    private fun showBottomNav() {
        navView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        navView.visibility = View.GONE
    }

}


