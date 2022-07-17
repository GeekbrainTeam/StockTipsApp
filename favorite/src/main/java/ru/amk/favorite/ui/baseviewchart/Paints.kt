package ru.amk.favorite.ui.baseviewchart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint

object Paints {

    val paintAxis: Paint = Paint().apply {
        color = Color.LTGRAY
        strokeWidth = 3f
    }

    val paintAxisDottedLine = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
        strokeWidth = 1f
        pathEffect = DashPathEffect(floatArrayOf(5f, 15f), 0f)
    }

    val paintText = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
        textSize = 32f
    }
}