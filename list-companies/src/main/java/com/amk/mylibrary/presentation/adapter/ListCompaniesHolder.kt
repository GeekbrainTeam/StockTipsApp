package com.amk.mylibrary.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.mylibrary.databinding.ItemCompanyBinding
import kotlin.math.abs


class ListCompaniesHolder(private val binding: ItemCompanyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(company: Company) {

        val formatePrice = formatePrice(company.entityCompany.close)


        binding.nameCompany.text = company.shortName
        binding.briefNameCompany.text = company.entityCompany.secId
        binding.nominalPrice.text =
            "${String.format("%$formatePrice", company.entityCompany.close)}  ₽"
        binding.changePrice.text = changePriceAndPercent(company)
    }

    private fun changePriceAndPercent(company: Company): String {
        val changePrice = company.changePrice
        val percent = company.changePercent
        val formateChangPrice = formatePrice(changePrice)

        if (changePrice > 0) {
            binding.changePrice.setTextColor(Color.GREEN)
        } else if (changePrice < 0) {
            binding.changePrice.setTextColor(Color.RED)
        } else {
            binding.changePrice.setTextColor(Color.GRAY)
        }
        return "${String.format("%$formateChangPrice", (changePrice))} ₽  (${
            String.format(
                "%.1f",
                (percent)
            )
        }%)"
    }

    private fun formatePrice(price: Double): String =
        if (abs(price) > 999) ".1f"
        else if (abs(price) > 9) ".2f"
        else if (abs(price) > 1) ".3f"
        else ".4f"

}


