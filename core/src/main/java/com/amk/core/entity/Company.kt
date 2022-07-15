package com.amk.core.entity

data class Company(
    val shortName: String,
    val entityCompany: EntityCompany,
    val changePrice: Double,
    val changePercent: Double,
    val favorite: Boolean
)
