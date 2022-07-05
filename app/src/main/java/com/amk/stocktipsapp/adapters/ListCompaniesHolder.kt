package com.amk.stocktipsapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.amk.stocktipsapp.databinding.ItemCompanyBinding
import com.amk.stocktipsapp.model.FakeModel

class ListCompaniesHolder(private val binding: ItemCompanyBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(commonModel: FakeModel) {
        if (binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = "фаворит"
        } else if (!binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = " не фаворит"
        }
        binding.nameCompany.text = commonModel.fakeName
        binding.nominalPrice.text = commonModel.fakeTotal.toString()
    }
}
