package com.amk.core.entity

import java.io.Serializable
import java.util.*

data class EntityCompany(
    val tradeDate: Date,
    val shortName: String,
    val secId: String,
    val open: Double,
    val low: Double,
    val high: Double,
    val close: Double
) : Serializable
