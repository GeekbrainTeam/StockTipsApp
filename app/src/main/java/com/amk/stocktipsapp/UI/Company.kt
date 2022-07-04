package com.amk.stocktipsapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.amk.stocktipsapp.R
import com.amk.stocktipsapp.databinding.FragmentCompanyBinding
import com.amk.stocktipsapp.databinding.FragmentFavoriteBinding
import com.amk.stocktipsapp.setSupportActionBarOneItem
import com.google.android.material.bottomappbar.BottomAppBar

class Company : Fragment() {
    private var _binding: FragmentCompanyBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setSupportActionBarOneItem (
            binding.bottomAppBar,
            navController,
            R.id.go_to_home3,
            R.id.action_company_to_listCompanyFragment)
    }
}