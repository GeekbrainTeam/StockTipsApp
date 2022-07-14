package com.amk.core.interactors

import com.amk.core.entity.Company

class SortingInteractorImpl(
    private val sourceCompanies: List<Company>,
) : SortingInteractor {

    override fun getSortingByName(): List<Company> =
        sourceCompanies.sortedBy { it.shortName }

    override fun getSortingByNameReverse(): List<Company> =
        sourceCompanies.sortedBy { it.shortName }.reversed()

    override fun getSortingByPrice(): List<Company> =
        sourceCompanies.sortedBy { it.entityCompany.close }

    override fun getSortingByPriceReverse(): List<Company> =
        sourceCompanies.sortedBy { it.entityCompany.close }.reversed()

    override fun getSortingByChangePrice(): List<Company> =
        sourceCompanies.sortedBy { it.changePrice }

    override fun getSortingByChangePriceReverse(): List<Company> =
        sourceCompanies.sortedBy { it.changePrice }

    override fun getSortingByChangePercent(): List<Company> =
        sourceCompanies.sortedBy { it.changePercent }

    override fun getSortingByChangePercentReverse(): List<Company> =
        sourceCompanies.sortedBy { it.changePercent }.reversed()
}