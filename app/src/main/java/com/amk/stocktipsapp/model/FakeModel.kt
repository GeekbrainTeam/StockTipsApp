package com.amk.stocktipsapp.model

import java.io.Serializable

data class FakeModel(
    val fakeName: String,
    val fakeShortName: String,
    val fakeChange: Double,
    val fakeChangePercent: Double,
    val fakePrice: Double,
    val fakeFavorite: Boolean
): Serializable
