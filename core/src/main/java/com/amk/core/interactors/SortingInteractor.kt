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
    fun getSortingByFavoriteName(): List<Company>
    fun getSortingByFavoriteNameReverse(): List<Company>
    fun getSortingByFavoritePrice(): List<Company>
    fun getSortingByFavoritePriceReverse(): List<Company>
    fun getSortingByFavoriteChangePrice(): List<Company>
    fun getSortingByFavoriteChangePriceReverse(): List<Company>
    fun getSortingByFavoriteChangePercent(): List<Company>
    fun getSortingByFavoriteChangePercentReverse(): List<Company>
}