package ru.amk.favorite.ui.baseviewchart

import android.graphics.Color
import android.graphics.Paint

sealed class ColorCandleFavorite(internal val paint: Paint) {

    class PriceDown : ColorCandleFavorite(Paint().apply {
        color = Color.RED
        strokeWidth = 5f
    })

    class PriceUp : ColorCandleFavorite(Paint().apply {
        color = Color.rgb(0, 128, 0)
        strokeWidth = 5f
    })
}

