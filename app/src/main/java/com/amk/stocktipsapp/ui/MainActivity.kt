package com.amk.stocktipsapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amk.core.navigation.AppNavigation
import com.amk.mylibrary.utils.KEY_PREF_THEME
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private val coordinator: AppNavigation by inject { parametersOf(this) }
    private val sharedPrefs by lazy { getSharedPreferences(KEY_PREF_THEME, Context.MODE_PRIVATE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initThemeListener()
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

    private fun initThemeListener() {
        when (sharedPrefs.getInt(KEY_PREF_THEME, 0)) {
            1 -> setCurrentTheme(AppCompatDelegate.MODE_NIGHT_NO)
            2 -> setCurrentTheme(AppCompatDelegate.MODE_NIGHT_YES)
            3 -> setCurrentTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    private fun setCurrentTheme(themeMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
    }

    private fun showBottomNav() {
        navView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        navView.visibility = View.GONE
    }
}


