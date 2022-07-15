package com.amk.mylibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amk.core.interactors.SortingInteractorImpl
import com.amk.core.repository.RepositoryCompany
import com.amk.mylibrary.utils.ListCompanyFragmentState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CompaniesListViewModel : ViewModel(), KoinComponent {

    private val repository by inject<RepositoryCompany>()
    private lateinit var sortingInteractorImpl: SortingInteractorImpl
    private val _companiesData =
        MutableLiveData<ListCompanyFragmentState>(ListCompanyFragmentState.Empty)
    val companiesData = _companiesData

    fun getCompanies() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {

            try {
                _companiesData.value =
                    ListCompanyFragmentState.Success(repository.CreateListOneDayYesterday())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByName() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByName(sortingInteractorImpl.getSortingByName())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByNameReverse() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByNameReverse(sortingInteractorImpl.getSortingByNameReverse())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByPrice() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByPrice(sortingInteractorImpl.getSortingByPrice())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByPriceReverse() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByPriceReverse(sortingInteractorImpl.getSortingByPriceReverse())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByChangePrice() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByChangePrice(sortingInteractorImpl.getSortingByChangePrice())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByChangePriceReverse() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByChangePriceReverse(sortingInteractorImpl.getSortingByChangePriceReverse())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByChangePercent() {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByChangePercent(sortingInteractorImpl.getSortingByChangePercent())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByChangePercentReverse() {
        viewModelScope.launch {
            try {
                val companiesList = repository.CreateListOneDayYesterday()
                sortingInteractorImpl = SortingInteractorImpl(companiesList)
                _companiesData.value =
                    ListCompanyFragmentState.SortByChangePercentReverse(sortingInteractorImpl.getSortingByChangePercentReverse())
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

}