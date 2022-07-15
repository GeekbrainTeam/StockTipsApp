package com.amk.core.entity

interface BaseCashCompany {
    val tradeDate: String
    val shortName: String
    val secId: String
    val open: Double
    val low: Double
    val high: Double
    val close: Double
}