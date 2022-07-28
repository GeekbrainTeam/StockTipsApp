package com.amk.core.utils

import com.amk.core.entity.EntityCompany

fun List<EntityCompany>.daysListToWeeksList(): List<EntityCompany> {
    val workList = this.toMutableList()
    val newList = mutableListOf<EntityCompany>()
    val weekList = mutableListOf<EntityCompany>()

    var startWeek = workList.last().tradeDate.convertToLocalDate()
    val numberDayOfWeek = startWeek.dayOfWeek.value

    startWeek = if (numberDayOfWeek < 7) {
        startWeek.minusDays(numberDayOfWeek.toLong())
    } else {
        startWeek.minusWeeks(1)
    }

    while (workList.isNotEmpty()) {
        weekList.clear()
        while (workList.size > 0 && workList.last().tradeDate > startWeek.convertToDate()) {
            weekList.add(workList.removeLast())
        }
        weekList.reverse()
        newList.add(weekToEntityCompany(weekList))
        startWeek = startWeek.minusWeeks(1)
    }
    newList.reverse()
    return newList
}

fun List<EntityCompany>.daysListToMonthsList(): List<EntityCompany> {
    val workList = this.toMutableList()
    val newList = mutableListOf<EntityCompany>()
    val weekList = mutableListOf<EntityCompany>()

    var startMonth = workList.last().tradeDate.convertToLocalDate()
    val numberDayOfMonth = startMonth.dayOfMonth
    startMonth = startMonth.minusDays((numberDayOfMonth).toLong())

    while (workList.isNotEmpty()) {
        weekList.clear()
        while (workList.size > 0 && workList.last().tradeDate > startMonth.convertToDate()) {
            weekList.add(workList.removeLast())
        }
        weekList.reverse()
        newList.add(weekToEntityCompany(weekList))
        startMonth = startMonth.minusMonths(1)
    }
    newList.reverse()
    return newList
}

private fun weekToEntityCompany(weekList: List<EntityCompany>): EntityCompany {
    return EntityCompany(
        weekList.last().tradeDate,
        weekList.first().shortName,
        weekList.first().secId,
        weekList.first().open,
        weekList.first().low,
        weekList.first().high,
        weekList.last().close
    )
}