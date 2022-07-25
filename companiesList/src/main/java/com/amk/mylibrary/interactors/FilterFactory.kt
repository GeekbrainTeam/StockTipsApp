package com.amk.mylibrary.interactors

import com.amk.core.entity.Company

class FilterFactory(
    private val listCompanyFragmentState: ListCompanyFragmentState?,
    private val charSequence: CharSequence?,
    private val sourceCompanies: List<Company>
) {
    fun searchFilter(): ListCompanyFragmentState {
        val result = mutableListOf<Company>()
        if (charSequence == null || charSequence.isEmpty()) {
            result.addAll(sourceCompanies)
        } else {
            result.addAll(sourceCompanies.filter {
                it.shortName.contains(charSequence) || it.entityCompany.secId.contains(
                    charSequence
                )
            })
        }
        return when (listCompanyFragmentState) {
            is ListCompanyFragmentState.SortByName -> {
                return ListCompanyFragmentState.SortByName(result)
            }
            is ListCompanyFragmentState.SortByNameReverse -> {
                return ListCompanyFragmentState.SortByNameReverse(result)
            }
            is ListCompanyFragmentState.SortByPrice -> {
                return ListCompanyFragmentState.SortByPrice(result)
            }
            is ListCompanyFragmentState.SortByPriceReverse -> {
                return ListCompanyFragmentState.SortByPriceReverse(result)
            }
            is ListCompanyFragmentState.SortByChangePrice -> {
                return ListCompanyFragmentState.SortByChangePrice(result)
            }
            is ListCompanyFragmentState.SortByChangePriceReverse -> {
                return ListCompanyFragmentState.SortByChangePriceReverse(result)
            }
            is ListCompanyFragmentState.SortByChangePercent -> {
                return ListCompanyFragmentState.SortByChangePercent(result)
            }
            is ListCompanyFragmentState.SortByChangePercentReverse -> {
                return ListCompanyFragmentState.SortByChangePercentReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByName -> {
                return ListCompanyFragmentState.SortFavoriteByName(result)
            }
            is ListCompanyFragmentState.SortFavoriteByNameReverse -> {
                return ListCompanyFragmentState.SortFavoriteByNameReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByPrice -> {
                return ListCompanyFragmentState.SortFavoriteByPrice(result)
            }
            is ListCompanyFragmentState.SortFavoriteByPriceReverse -> {
                return ListCompanyFragmentState.SortFavoriteByPriceReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePrice -> {
                return ListCompanyFragmentState.SortFavoriteByChangePrice(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePriceReverse -> {
                return ListCompanyFragmentState.SortFavoriteByChangePriceReverse(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePercent -> {
                return ListCompanyFragmentState.SortFavoriteByChangePercent(result)
            }
            is ListCompanyFragmentState.SortFavoriteByChangePercentReverse -> {
                return ListCompanyFragmentState.SortFavoriteByChangePercentReverse(result)
            }

            else -> {
                return ListCompanyFragmentState.SortByName(result)
            }
        }

    }
}