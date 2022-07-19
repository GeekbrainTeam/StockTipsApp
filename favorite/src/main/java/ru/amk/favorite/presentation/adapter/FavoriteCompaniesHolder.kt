package ru.amk.favorite.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.FavoriteCompany
import com.amk.core.interactors.FavoriteFactory
import com.amk.core.repository.NetworkRepository
import com.amk.core.retrofit.MoexApiImpl
import com.amk.core.retrofit.MoexApiService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.amk.favorite.databinding.ItemFavoriteBinding
import kotlin.math.abs


class FavoriteCompaniesHolder(
    private val binding: ItemFavoriteBinding,

) :
    RecyclerView.ViewHolder(binding.root) {
    val onDeleteClick: FloatingActionButton = binding.buttonDelete
    @SuppressLint("SetTextI18n")
    fun bind(favorite: FavoriteCompany) {
        binding.briefNameFavoriteTextview.text = favorite.secId

        //val formatePrice = formatPrice(company.entityCompany.close)


        /*binding.nameCompany.text = company.shortName
        binding.briefNameCompany.text = company.entityCompany.secId
        binding.nominalPrice.text =
            "${String.format("%$formatePrice", company.entityCompany.close)}  ₽"
        binding.nameCompany.text = company.shortName
        binding.briefNameCompany.text = company.entityCompany.secId
        binding.changePrice.text = changePriceAndPercent(company)

        binding.changePrice.text = changePriceAndPercent(company)
        binding.checkBoxFavorite.isChecked = company.favorite
        binding.checkBoxFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (binding.root.isAttachedToWindow) {
                if (isChecked) {
                    viewModel.addFavorite(company.entityCompany.secId)
                } else {
                    viewModel.deleteFavorite(company.entityCompany.secId)
                }
            }
        }*/
    }

    /*private fun changePriceAndPercent(company: Company): String {
        val changePrice = company.changePrice
        val percent = company.changePercent
        val formatChangPrice = formatPrice(company.entityCompany.close)

        if (changePrice > 0) {
            binding.changePrice.setTextColor(Color.GREEN)
        } else if (changePrice < 0) {
            binding.changePrice.setTextColor(Color.RED)
        } else {
            binding.changePrice.setTextColor(Color.GRAY)
        }
        return "${String.format("%$formatChangPrice", (changePrice))} ₽  (${
            String.format(
                "%.1f",
                (percent)
            )
        }%)"
    }*/

    private fun formatPrice(price: Double): String =
        if (abs(price) > 999) ".1f"
        else if (abs(price) > 9) ".2f"
        else if (abs(price) > 1) ".3f"
        else ".4f"

}


