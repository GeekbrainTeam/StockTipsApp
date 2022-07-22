package ru.amk.favorite.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.amk.core.entity.FavoriteCompanyShow
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.amk.favorite.databinding.ItemFavoriteBinding
import kotlin.math.abs


class FavoriteCompaniesHolder(
    private val binding: ItemFavoriteBinding,
) : RecyclerView.ViewHolder(binding.root) {

    val onDeleteClick: FloatingActionButton = binding.buttonDelete

    @SuppressLint("SetTextI18n")
    fun bind(favorite: FavoriteCompanyShow) {
        binding.briefNameFavoriteTextview.text = favorite.secId
        binding.nameFavoriteTextview.text = favorite.name
        binding.priceFavoriteTextview.text = favorite.price.toString()
        binding.textChangeDay.text =
            changePriceAndPercent(favorite.changePricePerDay, favorite.changePercentPerDay)
        binding.textChangeWeek.text =
            changePriceAndPercent(favorite.changePricePerWeek, favorite.changePercentPerWeek)
        binding.textChangeMonth.text =
            changePriceAndPercent(favorite.changePricePerMonth, favorite.changePercentPerMonth)

        binding.candlestickChart.drawCandles(favorite.listEntityCompany)
        binding.favoriteAxisYView.drawAxisY(favorite.listEntityCompany)
        binding.candleFavoriteSv.post {
            binding.candleFavoriteSv.scrollBy(binding.candleFavoriteSv.width, 0)
        }
        binding.candlestickChart.divScreen = 1.5
        binding.favoriteAxisYView.divScreen = 1.5
    }

    private fun changePriceAndPercent(changePrice: Double, changePercent: Double): String {
        val formatChangPrice = formatPrice(changePrice)

        if (changePrice > 0) {
            binding.textChangeDay.setTextColor(Color.GREEN)
            binding.textChangeWeek.setTextColor(Color.GREEN)
            binding.textChangeMonth.setTextColor(Color.GREEN)
        } else if (changePrice < 0) {
            binding.textChangeDay.setTextColor(Color.RED)
            binding.textChangeWeek.setTextColor(Color.RED)
            binding.textChangeMonth.setTextColor(Color.RED)
        } else {
            binding.textChangeDay.setTextColor(Color.GRAY)
            binding.textChangeWeek.setTextColor(Color.GRAY)
            binding.textChangeMonth.setTextColor(Color.GRAY)
        }
        return "${String.format("%$formatChangPrice", (changePrice))} ₽  (${
            String.format(
                "%.1f",
                (changePercent)
            )
        }%)"
    }

    private fun formatPrice(price: Double): String =
        if (abs(price) > 999) ".1f"
        else if (abs(price) > 9) ".2f"
        else if (abs(price) > 1) ".3f"
        else ".4f"
}


