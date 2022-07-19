package com.amk.core.interactors

import com.amk.core.entity.EntityCompany

class FavoriteFactory(
    private val present: EntityCompany,
    private val past: EntityCompany
) {
    fun changePriceFavorite(): Double {

        return past.close - present.close
    }

    fun changePercentFavorite(): Double {

        return (past.close - present.close) * 100 / present.close
    }

}