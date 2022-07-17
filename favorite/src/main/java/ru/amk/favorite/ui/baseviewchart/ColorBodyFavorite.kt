package ru.amk.favorite.ui.baseviewchart

import com.amk.core.entity.EntityCompany

fun EntityCompany.colorBody(): ColorCandleFavorite =
    if (open < close) {
        ColorCandleFavorite.PriceUp()
    } else {
        ColorCandleFavorite.PriceDown()
    }

fun List<EntityCompany>.minCandleList(): Double {
    return if (this.isNotEmpty()) {
        val min = this.map { it.low }.minOf { it }
        (min - min / 50)
    } else {
        0.0
    }
}