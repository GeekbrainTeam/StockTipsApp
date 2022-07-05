package com.amk.stocktipsapp.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.databinding.ActivityMainBinding
import com.amk.stocktipsapp.databinding.FragmentCompanyBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportFragmentManager.findFragmentById(R.id.company)!=null) {
            binding.bottomNavigationView.visibility = View.GONE
        }
        val navView : BottomNavigationView = binding.bottomNavigationView
        val navController: NavController = findNavController(R.id.fragment_container_view)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.go_to_home,
            R.id.go_to_favorite,
            R.id.go_to_settings,
            R.id.company
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }


}