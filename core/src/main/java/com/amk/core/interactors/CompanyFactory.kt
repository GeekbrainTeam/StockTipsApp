package com.amk.core.interactors

import com.amk.core.entity.Company
import com.amk.core.entity.EntityCompany

class CompanyFactory(
    private val currentDateCompanies: List<EntityCompany>,
    private val previousDateCompanies: List<EntityCompany>,
    private val listFavorite: List<String>,
) {

    fun getCompanies(): List<Company> {
        val result = mutableListOf<Company>()
        val commonListCompanies: MutableList<EntityCompany> = mutableListOf()
        currentDateCompanies.forEach { it1 ->
            if (previousDateCompanies.firstOrNull { it.secId == it1.secId } != null) {
                commonListCompanies.add(it1)
            }
        }
        commonListCompanies.map { commonCompany ->
            val currentDateCompany =
                currentDateCompanies.first { it.shortName == commonCompany.shortName }
            val previousDateCompany =
                previousDateCompanies.first { it.shortName == commonCompany.shortName }
            result.add(
                Company(
                    commonCompany.shortName,
                    currentDateCompanies.first { it.shortName == commonCompany.shortName },
                    currentDateCompany.close - previousDateCompany.close,
                    100 - currentDateCompany.close / previousDateCompany.close * 100,
                    listFavorite.contains(currentDateCompany.secId)
                )
            )
        }
        return result
    }
}