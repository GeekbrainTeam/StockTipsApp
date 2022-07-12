package com.amk.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.convertToString(): String {
    val sdf = SimpleDateFormat(FORMAT_DATA, Locale.getDefault())
    return sdf.format(this)
}

fun String.convertToDate(): Date {
    val sdf = SimpleDateFormat(FORMAT_DATA, Locale.getDefault())
    return sdf.parse(this) ?: Date()
}

fun Date.changeDay(days: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DATE, days)
    return c.time
}