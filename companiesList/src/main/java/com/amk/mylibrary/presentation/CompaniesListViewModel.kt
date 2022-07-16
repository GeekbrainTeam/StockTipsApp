package com.amk.mylibrary.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amk.core.entity.Company
import com.amk.core.interactors.SortingInteractorImpl
import com.amk.core.repository.RepositoryCompany
import com.amk.mylibrary.interactors.ListCompanyFragmentState
import com.amk.mylibrary.utils.DEFAULT_DIRECTION_SORT
import com.amk.mylibrary.utils.DEFAULT_TYPE_SORT
import com.amk.mylibrary.utils.Direction
import com.amk.mylibrary.utils.TypeSort
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CompaniesListViewModel : ViewModel(), KoinComponent {

    private val repository by inject<RepositoryCompany>()
    private val companyList: MutableList<Company> = mutableListOf()
    private var typeSort: TypeSort = DEFAULT_TYPE_SORT
    private var directionSort: Direction = DEFAULT_DIRECTION_SORT

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
                companyList.addAll(repository.createListOneDayYesterday())
                _companiesData.value =
                    ListCompanyFragmentState.Success(companyList)
                sortingInteractorImpl = SortingInteractorImpl(companyList)
                chooseSort(directionSort, typeSort)
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

    internal fun chooseSort(directionSort: Direction, typeSort: TypeSort) {
        this.directionSort = directionSort
        this.typeSort = typeSort
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
}