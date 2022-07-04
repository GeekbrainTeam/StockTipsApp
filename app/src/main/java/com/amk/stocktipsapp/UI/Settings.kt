package com.amk.stocktipsapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.databinding.FragmentSettingsBinding


class Settings : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        com.amk.stocktipsapp.setSupportActionBarTwoItem(
            binding.bottomAppBar,
            navController,
            R.id.go_to_home2,
            R.id.action_settings2_to_listCompanyFragment,
            R.id.favorite2,
            R.id.action_settings2_to_favorite2
        )
    }


}