package com.amk.company.ui.baseviewchart

import android.graphics.Color
import android.graphics.Paint

sealed class ColorCandle(internal val paint: Paint) {

    class PriceDown : ColorCandle(Paint().apply {
        color = Color.RED
        strokeWidth = 5f
    })

    class PriceUp : ColorCandle(Paint().apply {
        color = Color.rgb(0, 128, 0)
        strokeWidth = 5f
    })
}
