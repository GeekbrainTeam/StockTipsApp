package com.amk.mylibrary.utils

import androidx.core.view.isVisible
import com.amk.mylibrary.databinding.FragmentListCompanyBinding

class StatesCompanyList(private val binding: FragmentListCompanyBinding) {

    fun loading() {
        binding.recyclerViewCompanies.isVisible = false
        binding.progressBar.isVisible = true
    }

    fun error() {
        binding.recyclerViewCompanies.isVisible = false
        binding.progressBar.isVisible = false
    }

    fun success() {
        binding.recyclerViewCompanies.isVisible = true
        binding.progressBar.isVisible = false
    }
}