package com.amk.mylibrary.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.mylibrary.databinding.ItemCompanyBinding


class ListCompaniesHolder(private val binding: ItemCompanyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(entityCompany: Company) {
        binding.nameCompany.text = entityCompany.shortName
        binding.briefNameCompany.text = entityCompany.entityCompany.secId
        binding.nominalPrice.text = entityCompany.entityCompany.close.toString()
        binding.changePrice.text = entityCompany.changePrice.toString()
    }
}


