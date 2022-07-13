package com.amk.mylibrary.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.Company
import com.amk.core.entity.EntityCompany
import com.amk.mylibrary.databinding.ItemCompanyBinding
import kotlin.math.abs


class ListCompaniesHolder(private val binding: ItemCompanyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(entityCompany: Company) {

        //val formatePrice = formatePrice(entityCompany.close)


        binding.nameCompany.text = entityCompany.shortName
        binding.briefNameCompany.text = entityCompany.entityCompany.secId
        binding.nominalPrice.text = entityCompany.entityCompany.close.toString()
            //"${String.format("%$formatePrice", entityCompany.close)}  ₽"
        binding.changePrice.text = entityCompany.changePrice.toString()
    }

    /*private fun changePriceAndPercent(entityCompany: EntityCompany): String {
        val changePrice = entityCompany.close.calcChangePrice(entityCompany.open)
        val percent = entityCompany.close.calcChangePercent(entityCompany.open)
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

    private fun Double.calcChangePercent(openPrice: Double): Double {
        if (0.0 == openPrice) return 0.0
        return abs(100 - this / openPrice * 100)
    }

    private fun Double.calcChangePrice(openPrice: Double): Double = this - openPrice

    private fun formatePrice(price: Double): String =
        if (abs(price) > 999) ".1f"
        else if (abs(price) > 9) ".2f"
        else if (abs(price) > 1) ".3f"
        else ".4f"*/

}


