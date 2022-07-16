package com.amk.core.utils

import kotlin.math.abs

fun Double.changePrice(pricePreviousDate: Double): Double =
    this - pricePreviousDate

fun Double.percent(pricePreviousDate: Double): Double =
    abs(100 - (this / pricePreviousDate * 100))