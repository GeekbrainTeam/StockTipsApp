package com.amk.mylibrary.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amk.core.entity.Company
import com.amk.core.interactors.SortingInteractorImpl
import com.amk.core.repository.RepositoryCompany
import com.amk.mylibrary.interactors.ListCompanyFragmentState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CompaniesListViewModel : ViewModel(), KoinComponent {

    private val repository by inject<RepositoryCompany>()
    private val companyList: MutableList<Company> = mutableListOf()
    private var sortingInteractorImpl: SortingInteractorImpl = SortingInteractorImpl(companyList)
    private val _companiesData =
        MutableLiveData<ListCompanyFragmentState>(ListCompanyFragmentState.Empty)
    val companiesData = _companiesData

    init {
        _companiesData.value = ListCompanyFragmentState.Loading
        viewModelScope.launch {
            try {
                companyList.addAll(repository.createListOneDayYesterday())
                _companiesData.value =
                    ListCompanyFragmentState.Success(companyList)
                sortingInteractorImpl = SortingInteractorImpl(companyList)
            } catch (error: Exception) {
                _companiesData.value = ListCompanyFragmentState.Failure(error)
            }
        }
    }

    fun getSortedByName() {
        _companiesData.value =
            ListCompanyFragmentState.SortByName(sortingInteractorImpl.getSortingByName())
    }

    fun getSortedByNameReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByNameReverse(sortingInteractorImpl.getSortingByNameReverse())
    }

    fun getSortedByPrice() {
        _companiesData.value =
            ListCompanyFragmentState.SortByPrice(sortingInteractorImpl.getSortingByPrice())
    }

    fun getSortedByPriceReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByPriceReverse(sortingInteractorImpl.getSortingByPriceReverse())
    }

    fun getSortedByChangePrice() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePrice(sortingInteractorImpl.getSortingByChangePrice())
    }

    fun getSortedByChangePriceReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePriceReverse(sortingInteractorImpl.getSortingByChangePriceReverse())
    }

    fun getSortedByChangePercent() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePercent(sortingInteractorImpl.getSortingByChangePercent())
    }

    fun getSortedByChangePercentReverse() {
        _companiesData.value =
            ListCompanyFragmentState.SortByChangePercentReverse(sortingInteractorImpl.getSortingByChangePercentReverse())
    }

}