package com.amk.core.entity

data class FavoriteCompany(
    val secId: String,
    val name: String,
    val price: Double,
    val listEntityCompany: List<EntityCompany>,
    val changePricePerDay: Double,
    val changePercentPerDay: Double,
    val changePricePerWeek: Double,
    val changePercentPerWeek: Double,
    val changePricePerMonth: Double,
    val changePercentPerMonth: Double,
    val favorite: Boolean
)
