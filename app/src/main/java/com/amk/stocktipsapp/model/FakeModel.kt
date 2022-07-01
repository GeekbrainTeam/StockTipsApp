package com.amk.stocktipsapp.model

import java.io.Serializable

data class FakeModel(
    val fakeName: String,
    val fakeTotal: Double,
    val fakeFavorite: Boolean
): Serializable
