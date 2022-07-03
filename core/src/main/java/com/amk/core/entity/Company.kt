package com.amk.core.entity

import java.util.*

data class Company(
    val containsNulls: Boolean,
    val tradeDate: Date,
    val shortName: String,
    val secId: String,
    val open: Double = 0.0,
    val low: Double = 0.0,
    val high: Double = 0.0,
    val close: Double = 0.0
)