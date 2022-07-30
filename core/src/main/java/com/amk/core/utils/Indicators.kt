package com.amk.core.utils

import com.amk.core.entity.EntityCompany
import kotlin.math.pow
import kotlin.math.sqrt

fun List<EntityCompany>.calculateMaxPriceDrop(): Double {
    var fromEntity = this[0]
    var toEntity = this[0]
    var maxDrop = 0.0

    for (index in this.indices) {
        if (this[index].close >= fromEntity.close) {
            val minPrice = findMinPrice(this, index)
            val drop = this[index].close - minPrice.close
            if (drop > maxDrop) {
                fromEntity = this[index]
                toEntity = minPrice
                maxDrop = drop
            }
        }
    }
    return (fromEntity.close - toEntity.close) / fromEntity.close
}

private fun findMinPrice(companyList: List<EntityCompany>, max: Int): EntityCompany {
    var min = companyList[max]
    for (index in max until companyList.size) {
        if (companyList[index].close < min.close) {
            min = companyList[index]
        }
    }
    return min
}

fun List<EntityCompany>.calculateYield(): Double {
    val startPrice = this.first().close
    val yield = this.last().close - startPrice
    return yield / startPrice
}

fun List<EntityCompany>.calculateVolatile(): Double {
    val dPkMid = dPkMid(this)
    var sum = 0.0
    for (k in 1 until this.size) {
        sum += (dPk(this, k) - dPkMid).pow(2)
    }
    return sqrt(sum / (this.size - 1)) * sqrt(this.size.toDouble())
}

private fun dPkMid(companyList: List<EntityCompany>): Double {
    var sum = 0.0
    for (k in 1 until companyList.size) {
        sum += dPk(companyList, k)
    }
    return sum / (companyList.size - 1)
}

private fun dPk(companyList: List<EntityCompany>, k: Int): Double {
    return (companyList[k].close - companyList[k - 1].close) / companyList[k - 1].close
}

fun List<EntityCompany>.minPrice(): Double {
    var min = this[0].close
    for (index in 1 until this.size) {
        if (this[index].close < min) {
            min = this[index].close
        }
    }
    return min
}

fun List<EntityCompany>.maxPrice(): Double {
    var max = this[0].close
    for (index in 1 until this.size) {
        if (this[index].close > max) {
            max = this[index].close
        }
    }
    return max
}

