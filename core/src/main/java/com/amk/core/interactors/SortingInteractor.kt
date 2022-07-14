package com.amk.core.interactors

import com.amk.core.entity.Company

interface SortingInteractor {

    fun getSortingByName(): List<Company>
    fun getSortingByNameReverse(): List<Company>
    fun getSortingByPrice(): List<Company>
    fun getSortingByPriceReverse(): List<Company>
    fun getSortingByChangePrice(): List<Company>
    fun getSortingByChangePriceReverse(): List<Company>
    fun getSortingByChangePercent(): List<Company>
    fun getSortingByChangePercentReverse(): List<Company>
}