package com.amk.mylibrary.interactors

import com.amk.core.entity.Company

sealed class ListCompanyFragmentState{
    object Loading : ListCompanyFragmentState()
    class Failure(val msg:Throwable) : ListCompanyFragmentState()
    class SortByName(val data:List<Company>) : ListCompanyFragmentState()
    class SortByNameReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortByPrice(val data:List<Company>) : ListCompanyFragmentState()
    class SortByPriceReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePrice(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePriceReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePercent(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePercentReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByName(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByNameReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByPrice(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByPriceReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByChangePrice(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByChangePriceReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByChangePercent(val data:List<Company>) : ListCompanyFragmentState()
    class SortFavoriteByChangePercentReverse(val data:List<Company>) : ListCompanyFragmentState()
    class Filter (val data:List<Company>) : ListCompanyFragmentState()
    object Empty : ListCompanyFragmentState()
}
