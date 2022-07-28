package com.amk.stocktipsapp.ui

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.amk.core.navigation.AppNavigation
import com.amk.mylibrary.utils.KEY_PREF_THEME
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.UI.SearchFromAll
import ru.amk.favorite.ui.SearchFromFavorite
import com.amk.stocktipsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), SearchFromAll, SearchFromFavorite {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private val coordinator: AppNavigation by inject { parametersOf(this) }
    private val sharedPrefs by lazy { getSharedPreferences(KEY_PREF_THEME, Context.MODE_PRIVATE) }
    private var searshWord = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initThemeListener()
        val toolbar = supportActionBar
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(msg: String): Boolean {
                searshWord=msg
                //getQueryCompany()
                //getQueryFavoriteCompany()
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.actionSearch) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getQueryCompany() : LiveData<String> {
        return MutableLiveData(searshWord)
    }

    override fun getQueryFavoriteCompany(): LiveData<String> {
        return MutableLiveData(searshWord)
    }
}


