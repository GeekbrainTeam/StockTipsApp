package com.amk.core.interactors

import com.amk.core.entity.EntityCompany
import com.amk.core.entity.FavoriteCompany

class FavoriteFactory(
    private val dataCompany: List<EntityCompany>,
) {
    private val currentDay = dataCompany.last()
    private val changePerDay =
        if (dataCompany.size > 2) dataCompany[dataCompany.size - 2] else dataCompany.last()
    private val changePerWeek =
        if (dataCompany.size > 7) dataCompany[dataCompany.size - 3] else dataCompany.last()
    private val changePerMonth =
        if (dataCompany.size > 31) dataCompany[dataCompany.size - 31] else dataCompany.last()

    fun getFavoriteCompany(): FavoriteCompany =
        FavoriteCompany(
            secId = dataCompany.first().secId,
            name = dataCompany.first().shortName,
            price = dataCompany.last().close,
            listEntityCompany = dataCompany,
            changePricePerDay = changePriceFavorite(
                currentDay, changePerDay
            ),
            changePercentPerDay = changePercentFavorite(currentDay, changePerDay),
            changePricePerWeek = changePriceFavorite(currentDay, changePerWeek),
            changePercentPerWeek = changePercentFavorite(currentDay, changePerWeek),
            changePricePerMonth = changePriceFavorite(currentDay, changePerMonth),
            changePercentPerMonth = changePercentFavorite(currentDay, changePerMonth),
            favorite = true
        )


    fun changePriceFavorite(today: EntityCompany, lastDay: EntityCompany): Double =
        today.close - lastDay.close


    fun changePercentFavorite(today: EntityCompany, lastDay: EntityCompany): Double =
        (today.close - lastDay.close) * 100 / lastDay.close

}