package com.amk.company.ui.baseviewchart

import com.amk.core.entity.EntityCompany

fun EntityCompany.colorBody(): ColorCandle =
    if (open < close) {
        ColorCandle.PriceUp()
    } else {
        ColorCandle.PriceDown()
    }

fun List<EntityCompany>.minCandleList(): Double {
    return if (this.isNotEmpty()) {
        val min = this.map { it.low }.minOf { it }
        (min - min / 50)
    } else {
        0.0
    }
}