package com.amk.mylibrary.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amk.core.entity.Company
import com.amk.core.interactors.SortingInteractorImpl
import com.amk.core.repository.RepositoryCompany
import com.amk.mylibrary.interactors.ListCompanyFragmentState
import com.amk.mylibrary.utils.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CompaniesListViewModel : ViewModel(), KoinComponent {

    private val repository by inject<RepositoryCompany>()
    private val companyList: MutableList<Company> = mutableListOf()
    private var typeSort: TypeSort = DEFAULT_TYPE_SORT
    private var directionSort: Direction = DEFAULT_DIRECTION_SORT
    private var firstElements: FavoriteState = DEFAULT_FIRST

    private var sortingInteractorImpl: SortingInteractorImpl = SortingInteractorImpl(companyList)
    private val _companiesData =
        MutableLiveData<ListCompanyFragmentState>(ListCompanyFragmentState.Empty)
    val companiesData = _companiesData

    init {
        _companiesData.value = ListCompanyFragmentState.Loading
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch {
            try {
                repository.createListOneDayYesterday().collect {
                    companyList.clear()
                    companyList.addAll(it)
                    _companiesData.value =
                        ListCompanyFragmentState.Success(companyList)
                    sortingInteractorImpl = SortingInteractorImpl(companyList)
                    chooseSort(directionSort, typeSort)
                }

            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun addFavorite(secId: String) {
        viewModelScope.launch {
            try {
                repository.addFavoriteCompany(secId)
                companyList.clear()
                updateData()
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun deleteFavorite(secId: String) {
        viewModelScope.launch {
            try {
                repository.deleteFavoriteCompany(secId)
                companyList.clear()
                updateData()
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    internal fun chooseSort(
        directionSort: Direction,
        typeSort: TypeSort,
        firstElements: FavoriteState = this.firstElements
    ) {
        this.directionSort = directionSort
        this.typeSort = typeSort
        this.firstElements = firstElements
        when (firstElements) {
            FavoriteState.FavoriteMix -> {
                when (directionSort) {
                    Direction.Up -> {
                        when (typeSort) {
                            TypeSort.Name -> getSortedByName()
                            TypeSort.Price -> getSortedByPrice()
                            TypeSort.ChangePrice -> getSortedByChangePrice()
                            TypeSort.Percent -> getSortedByChangePercent()
                        }
                    }
                    Direction.Down -> {
                        when (typeSort) {
                            TypeSort.Name -> getSortedByNameReverse()
                            TypeSort.Price -> getSortedByPriceReverse()
                            TypeSort.ChangePrice -> getSortedByChangePriceReverse()
                            TypeSort.Percent -> getSortedByChangePercentReverse()
                        }
                    }
                }
            }
            FavoriteState.FavoriteUp -> {
                when (directionSort) {
                    Direction.Up -> {
                        when (typeSort) {
                            TypeSort.Name -> getSortedFavoriteByName()
                            TypeSort.Price -> getSortedFavoriteByPrice()
                            TypeSort.ChangePrice -> getSortedFavoriteByChangePrice()
                            TypeSort.Percent -> getSortedFavoriteByChangePercent()
                        }
                    }
                    Direction.Down -> {
                        when (typeSort) {
                            TypeSort.Name -> getSortedFavoriteByNameReverse()
                            TypeSort.Price -> getSortedFavoriteByPriceReverse()
                            TypeSort.ChangePrice -> getSortedFavoriteByChangePriceReverse()
                            TypeSort.Percent -> getSortedFavoriteByChangePercentReverse()
                        }
                    }
                }
            }
        }
    }

    private fun getSortedByName() {
        _companiesData.value =
            ListCompanyFragmentState.SortByName(sortingInteractorImpl.getSortingByName())
    }

    private fun getSortedByNameReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByNameReverse(sortingInteractorImpl.getSortingByNameReverse())
    }

    private fun getSortedByPrice() {
        _companiesData.value =
            ListCompanyFragmentState.SortByPrice(sortingInteractorImpl.getSortingByPrice())
    }

    private fun getSortedByPriceReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByPriceReverse(sortingInteractorImpl.getSortingByPriceReverse())
    }

    private fun getSortedByChangePrice() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePrice(sortingInteractorImpl.getSortingByChangePrice())
    }

    private fun getSortedByChangePriceReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePriceReverse(sortingInteractorImpl.getSortingByChangePriceReverse())
    }

    private fun getSortedByChangePercent() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePercent(sortingInteractorImpl.getSortingByChangePercent())
    }

    private fun getSortedByChangePercentReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePercentReverse(sortingInteractorImpl.getSortingByChangePercentReverse())
    }

    private fun getSortedFavoriteByName() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByName(sortingInteractorImpl.getSortingByFavoriteName())
    }

    private fun getSortedFavoriteByNameReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByNameReverse(sortingInteractorImpl.getSortingByFavoriteNameReverse())
    }

    private fun getSortedFavoriteByPrice() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByPrice(sortingInteractorImpl.getSortingByFavoritePrice())
    }

    private fun getSortedFavoriteByPriceReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByPriceReverse(sortingInteractorImpl.getSortingByFavoritePriceReverse())
    }

    private fun getSortedFavoriteByChangePrice() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByChangePrice(sortingInteractorImpl.getSortingByFavoriteChangePrice())
    }

    private fun getSortedFavoriteByChangePriceReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByChangePriceReverse(sortingInteractorImpl.getSortingByFavoriteChangePriceReverse())
    }

    private fun getSortedFavoriteByChangePercent() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByChangePercent(sortingInteractorImpl.getSortingByFavoriteChangePercent())
    }

    private fun getSortedFavoriteByChangePercentReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortFavoriteByChangePercent(sortingInteractorImpl.getSortingByFavoriteChangePercentReverse())
    }
}