package com.amk.core.entity

import java.io.Serializable

data class CompanyIsShow(
    val shortName: String,
    val changePrice: Double,
    val changePercent: Double,
    val favorite: Boolean
): Serializable
