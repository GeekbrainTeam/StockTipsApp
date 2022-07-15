package com.amk.mylibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amk.core.entity.Company
import com.amk.core.entity.EntityCompany
import com.amk.core.repository.Repository
import com.amk.core.repository.RepositoryCompany
import com.amk.core.repository.RepositoryCompanyImpl
import kotlinx.coroutines.*

class CompaniesListViewModel : ViewModel() {
    private lateinit var repository: RepositoryCompany
    val companiesListDataYesterday = MutableLiveData<List<Company>>()
    val companiesListDataHalfYear = MutableLiveData<List<Company>>()
    val errorData = MutableLiveData<String>()
    private var job: Job? = null
    private val scope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private fun handleError(error: Throwable) {
        scope.launch {
            errorData.postValue(error.toString())
        }
    }

    fun getCompanies() {
        job?.cancel()
        job = scope.launch {
            val companiesList = repository.CreateListOneDayYesterday()
            companiesListDataYesterday.postValue(companiesList)
        }
    }

    fun setRepo(repository: RepositoryCompany) {
        this.repository = repository
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}