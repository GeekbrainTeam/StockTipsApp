package com.amk.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringU(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}

fun String.toDateU(): Date {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.parse(this) ?: Date()
}

fun Date.changeDayU(days: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DATE, days)
    return c.time
}