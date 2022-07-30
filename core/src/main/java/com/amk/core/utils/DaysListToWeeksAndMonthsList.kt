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
        while (workList.isNotEmpty() && workList.last().tradeDate > startWeek.convertToDate()) {
            weekList.add(workList.removeLast())
        }

        if (weekList.isNotEmpty()) {
            weekList.reverse()
            newList.add(listToEntityCompany(weekList))
        }
        startWeek = startWeek.minusWeeks(1)
    }
    newList.reverse()
    return newList
}

fun List<EntityCompany>.daysListToMonthsList(): List<EntityCompany> {
    val workList = this.toMutableList()
    val newList = mutableListOf<EntityCompany>()
    val mothList = mutableListOf<EntityCompany>()

    var startMonth = workList.last().tradeDate.convertToLocalDate()
    val numberDayOfMonth = startMonth.dayOfMonth
    startMonth = startMonth.minusDays((numberDayOfMonth).toLong())

    while (workList.isNotEmpty()) {
        mothList.clear()
        while (workList.isNotEmpty() && workList.last().tradeDate > startMonth.convertToDate()) {
            mothList.add(workList.removeLast())
        }

        if (mothList.isNotEmpty()) {
            mothList.reverse()
            newList.add(listToEntityCompany(mothList))
        }
        startMonth = startMonth.minusMonths(1)
    }
    newList.reverse()
    return newList
}

private fun listToEntityCompany(weekList: List<EntityCompany>): EntityCompany {
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