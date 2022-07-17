package com.amk.core.interactors

import com.amk.core.entity.ColorCandle
import com.amk.core.entity.EntityCompany
import com.amk.core.entity.ThreeLineBreakCompany

class ThreeLineBreakCompanyFactory(
    private val companies: List<EntityCompany>
) {
    private val threeLineBreakCompanyList: MutableList<ThreeLineBreakCompany> = mutableListOf()

    fun getThreeLineCompanies(): List<ThreeLineBreakCompany> {

        threeLineBreakCompanyList.add(companies.first().convertToThreeLine())

        if (companies.isEmpty()) return emptyList()
        companies.forEach {
            val maxPrice = threeLineBreakCompanyList.last().maxPrice
            val minPrice = threeLineBreakCompanyList.last().minPrice
            if (it.close > maxPrice) {
                threeLineBreakCompanyList.add(
                    ThreeLineBreakCompany(
                        maxPrice = it.close,
                        minPrice = maxPrice,
                        date = it.tradeDate,
                        color = ColorCandle.PriceUp()
                    )
                )
            } else if (it.close < minPrice) {
                threeLineBreakCompanyList.add(
                    ThreeLineBreakCompany(
                        maxPrice = minPrice,
                        minPrice = it.close,
                        date = it.tradeDate,
                        color = ColorCandle.PriceDown()
                    )
                )
            }
        }
        return threeLineBreakCompanyList
    }

    private fun EntityCompany.convertToThreeLine(): ThreeLineBreakCompany =
        ThreeLineBreakCompany(
            close, close, tradeDate, ColorCandle.PriceUp()
        )
}
