package com.amk.stocktipsapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.databinding.FragmentFavoriteBinding
import com.google.android.material.bottomappbar.BottomAppBar


class Favorite : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setSupportActionBar(binding.bottomAppBar)
    }

    private fun setSupportActionBar(toolbarMain: BottomAppBar) {
        toolbarMain.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.go_to_home -> {
                    navController.navigate(R.id.action_favorite2_to_listCompanyFragment)
                    true
                }
                R.id.settings2 -> {
                    navController.navigate(R.id.action_favorite2_to_settings2)
                    true
                }
                else -> false
            }
        }
    }
}