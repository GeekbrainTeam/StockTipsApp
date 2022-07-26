package com.amk.mylibrary.interactors

import com.amk.core.entity.Company
import com.amk.core.interactors.SortingInteractorImpl

class FilterFactory(
    private val listCompanyFragmentState: ListCompanyFragmentState?,
    private val charSequence: CharSequence?,
    private var sortingInteractorImpl: SortingInteractorImpl
    //private val sourceCompanies: List<Company>
) {
    fun searchFilter(): ListCompanyFragmentState {
        //val result = filterList()
        when (listCompanyFragmentState) {
            is ListCompanyFragmentState.SortByName -> {
                val sortingList = sortingInteractorImpl.getSortingByName()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByName(result)
            }
            is ListCompanyFragmentState.SortByNameReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByNameReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByNameReverse(result)
            }
            is ListCompanyFragmentState.SortByPrice -> {
                val sortingList = sortingInteractorImpl.getSortingByPrice()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByPrice(result)
            }
            is ListCompanyFragmentState.SortByPriceReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByPriceReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByPriceReverse(result)
            }
            is ListCompanyFragmentState.SortByChangePrice -> {
                val sortingList = sortingInteractorImpl.getSortingByChangePrice()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByChangePrice(result)
            }
            is ListCompanyFragmentState.SortByChangePriceReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByChangePriceReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByChangePriceReverse(result)
            }
            is ListCompanyFragmentState.SortByChangePercent -> {
                val sortingList = sortingInteractorImpl.getSortingByChangePercent()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByChangePercent(result)
            }
            is ListCompanyFragmentState.SortByChangePercentReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByChangePercentReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortByChangePercentReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByName -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoriteName()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByName(result)
            }
            is ListCompanyFragmentState.SortFavoriteByNameReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoriteNameReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByNameReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByPrice -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoritePrice()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByPrice(result)
            }
            is ListCompanyFragmentState.SortFavoriteByPriceReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoritePriceReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByPriceReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePrice -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoriteChangePrice()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByChangePrice(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePriceReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoriteChangePriceReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByChangePriceReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePercent -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoriteChangePercent()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByChangePercent(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePercentReverse -> {
                val sortingList = sortingInteractorImpl.getSortingByFavoriteChangePercentReverse()
                val result = filterList(sortingList)
                return ListCompanyFragmentState.SortFavoriteByChangePercentReverse(result)
            }
            ListCompanyFragmentState.Empty -> TODO()
            is ListCompanyFragmentState.Failure -> TODO()
            is ListCompanyFragmentState.Filtered -> TODO()
            ListCompanyFragmentState.Loading -> TODO()
            null -> TODO()
        }

    }

    private fun filterList(sort:  List<Company>): List<Company> {
        return if (charSequence.toString().isEmpty()) {
            sort
        } else {
            sort.filter {
                it.entityCompany.secId.contains(charSequence.toString(), true) ||
                        it.entityCompany.shortName.contains(charSequence.toString(), true)
            }

        }
    }
}