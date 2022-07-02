package com.amk.core.entity

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

fun Date.yesterdayU(): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DATE, -1)
    return c.time
}