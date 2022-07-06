package com.amk.stocktipsapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amk.core.navigation.AppNavigation
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private val coordinator: AppNavigation by inject { parametersOf(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.bottomNavigationView

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.go_to_home,
                R.id.go_to_favorite,
                R.id.go_to_settings,
                R.id.company
            )
        )
        setupActionBarWithNavController(coordinator.navController, appBarConfiguration)
        navView.setupWithNavController(coordinator.navController)
        coordinator.navController.addOnDestinationChangedListener { _, destination, _ ->
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


