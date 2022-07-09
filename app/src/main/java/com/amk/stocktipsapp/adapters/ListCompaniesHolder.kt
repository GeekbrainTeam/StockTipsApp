package com.amk.stocktipsapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.amk.stocktipsapp.databinding.ItemCompanyBinding
import com.amk.stocktipsapp.model.FakeModel

class ListCompaniesHolder(private val binding: ItemCompanyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(commonModel: FakeModel) {
        if (binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = "фаворит"
        } else if (!binding.checkBoxFavorite.isChecked) {
            binding.briefNameCompany.text = " не фаворит"
        }
        binding.nameCompany.text = commonModel.fakeName
        binding.briefNameCompany.text = commonModel.fakeShortName
        binding.nominalPrice.text = "${commonModel.fakePrice} ₽ "
        binding.changePrice.text =
            "${commonModel.fakeChange} ₽  (${commonModel.fakeChangePercent}%)"
        if (commonModel.fakeChange > 0) {
            binding.changePrice.setTextColor(Color.GREEN)
        } else if (commonModel.fakeChange < 0) {
            binding.changePrice.setTextColor(Color.RED)
        } else {
            binding.changePrice.setTextColor(Color.GRAY)
        }
    }
}
