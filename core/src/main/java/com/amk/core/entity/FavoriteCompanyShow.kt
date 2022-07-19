package com.amk.core.entity

data class FavoriteCompanyShow(
    val secId: String,
    val name: String,
    val listEntityCompany: List<EntityCompany>,
    val changePricePerDay: Double,
    val changePercentPerDay: Double,
    val changePricePerWeek: Double,
    val changePercentPerWeek: Double,
    val changePricePerMonth: Double,
    val changePercentPerMonth: Double,
    val favorite: Boolean
)
