package com.amk.core.interactors

import com.amk.core.entity.EntityCompany

class FavoriteFactory(
    private val today: EntityCompany,
    private val lastDay: EntityCompany
) {
    fun changePriceFavorite(): Double {

        return today.close-lastDay.close
    }

    fun changePercentFavorite(): Double {

        return (today.close-lastDay.close) * 100 / lastDay.close
    }

}