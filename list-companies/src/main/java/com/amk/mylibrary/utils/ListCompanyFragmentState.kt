package com.amk.mylibrary.utils

import com.amk.core.entity.Company

sealed class ListCompanyFragmentState{
    object Loading : ListCompanyFragmentState()
    class Failure(val msg:Throwable) : ListCompanyFragmentState()
    class Success(val data:List<Company>) : ListCompanyFragmentState()
    class SortByName(val data:List<Company>) : ListCompanyFragmentState()
    class SortByNameReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortByPrice(val data:List<Company>) : ListCompanyFragmentState()
    class SortByPriceReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePrice(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePriceReverse(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePercent(val data:List<Company>) : ListCompanyFragmentState()
    class SortByChangePercentReverse(val data:List<Company>) : ListCompanyFragmentState()
    object Empty : ListCompanyFragmentState()
}
