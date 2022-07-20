package com.amk.core.interactors

import com.amk.core.entity.Company
import kotlin.math.abs

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
        sourceCompanies.sortedBy { it.changePrice }.reversed()

    override fun getSortingByChangePercent(): List<Company> =
        sourceCompanies.sortedBy { abs(it.changePercent) }

    override fun getSortingByChangePercentReverse(): List<Company> =
        sourceCompanies.sortedBy { abs(it.changePercent) }.reversed()

    override fun getSortingByFavoriteName() =
        addToFavoriteLists().sortedBy { it.shortName } + addToNotFavoriteLists().sortedBy { it.shortName }

    override fun getSortingByFavoriteNameReverse() =
        addToFavoriteLists().sortedBy { it.shortName }
            .reversed() + addToNotFavoriteLists().sortedBy { it.shortName }.reversed()

    override fun getSortingByFavoritePrice() =
        addToFavoriteLists().sortedBy { it.entityCompany.close } + addToNotFavoriteLists().sortedBy { it.entityCompany.close }

    override fun getSortingByFavoritePriceReverse() =
        addToFavoriteLists().sortedBy { it.entityCompany.close }
            .reversed() + addToNotFavoriteLists().sortedBy { it.entityCompany.close }.reversed()

    override fun getSortingByFavoriteChangePrice() =
        addToFavoriteLists().sortedBy { it.changePrice } + addToNotFavoriteLists().sortedBy { it.changePrice }

    override fun getSortingByFavoriteChangePriceReverse() =
        addToFavoriteLists().sortedBy { it.changePrice }
            .reversed() + addToNotFavoriteLists().sortedBy { it.changePrice }.reversed()

    override fun getSortingByFavoriteChangePercent() =
        addToFavoriteLists().sortedBy { abs(it.changePercent) } + addToNotFavoriteLists().sortedBy {
            abs(it.changePercent)
        }

    override fun getSortingByFavoriteChangePercentReverse() =
        addToFavoriteLists().sortedBy { abs(it.changePercent) }
            .reversed() + addToNotFavoriteLists().sortedBy { abs(it.changePercent) }.reversed()

    private fun addToFavoriteLists(): List<Company> {
        val favoriteList = mutableListOf<Company>()
        favoriteList.addAll(sourceCompanies.filter { it.favorite })
        return favoriteList
    }

    private fun addToNotFavoriteLists(): List<Company> {
        val notFavoriteList = mutableListOf<Company>()
        notFavoriteList.addAll(sourceCompanies.filter { !it.favorite })
        return notFavoriteList
    }

}