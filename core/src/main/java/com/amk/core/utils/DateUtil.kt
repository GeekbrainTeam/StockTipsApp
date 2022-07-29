package com.amk.core.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
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

fun Date.convertForXAxisLessYear(): String {
    val sdf = SimpleDateFormat(SHORT_FARMAT_DATA, Locale.getDefault())
    return sdf.format(this)
}

fun Date.convertForXAxisMoreYear(): String {
    val sdf = SimpleDateFormat(FORMAT_DATA, Locale.getDefault())
    return sdf.format(this)
}



fun Date.convertToLocalDate(): LocalDate = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

fun LocalDate.convertToDate(): Date = Date.from(this.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
