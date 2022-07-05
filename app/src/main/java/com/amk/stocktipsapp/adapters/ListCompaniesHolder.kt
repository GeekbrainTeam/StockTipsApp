package com.amk.stocktipsapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.stocktipsapp.databinding.ItemCompanyBinding

class ListCompaniesHolder(private val binding: ItemCompanyBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(company: Company) {
        /*if (binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = "фаворит"
        } else if (!binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = " не фаворит"
        }*/
        binding.nameCompany.text = company.shortName
        binding.briefNameCompany.text = company.secId
        binding.nominalPrice.text = String.format("%.3f", company.close)
        binding.changePrice.text = String.format("%.3f", (company.close - company.open))    }
}
