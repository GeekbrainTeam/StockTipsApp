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

    override fun getSortingByFavoriteName(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { it.shortName }
        isNotFavoriteList.sortedBy { it.shortName }
        return isFavoriteList + isNotFavoriteList
    }

    override fun getSortingByFavoriteNameReverse(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { it.shortName }.reversed()
        isNotFavoriteList.sortedBy { it.shortName }.reversed()
        return isFavoriteList + isNotFavoriteList
    }

    override fun getSortingByFavoritePrice(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { it.entityCompany.close }
        isNotFavoriteList.sortedBy { it.entityCompany.close }
        return isFavoriteList + isNotFavoriteList
    }

    override fun getSortingByFavoritePriceReverse(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { it.entityCompany.close }.reversed()
        isNotFavoriteList.sortedBy { it.entityCompany.close }.reversed()
        return isFavoriteList + isNotFavoriteList
    }

    override fun getSortingByFavoriteChangePrice(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { it.changePrice }
        isNotFavoriteList.sortedBy { it.changePrice }
        return isFavoriteList + isNotFavoriteList
    }

    override fun getSortingByFavoriteChangePriceReverse(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { it.changePrice }.reversed()
        isNotFavoriteList.sortedBy { it.changePrice }.reversed()
        return isFavoriteList + isNotFavoriteList
    }

    override fun getSortingByFavoriteChangePercent(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { abs(it.changePercent) }
        isNotFavoriteList.sortedBy { abs(it.changePercent) }
        return isFavoriteList + isNotFavoriteList
    }

    override fun getSortingByFavoriteChangePercentReverse(): List<Company> {
        val isFavoriteList = mutableListOf<Company>()
        val isNotFavoriteList = mutableListOf<Company>()
        sourceCompanies.forEach {
            if (it.favorite) {
                isFavoriteList.add(it)
            } else {
                isNotFavoriteList.add(it)
            }
        }
        isFavoriteList.sortedBy { abs(it.changePercent) }.reversed()
        isNotFavoriteList.sortedBy { abs(it.changePercent) }.reversed()
        return isFavoriteList + isNotFavoriteList
    }
}