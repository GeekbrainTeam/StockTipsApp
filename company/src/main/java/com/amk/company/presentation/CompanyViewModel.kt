package com.amk.company.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amk.core.entity.EntityCompany
import com.amk.core.repository.Repository
import com.amk.core.utils.changeDay
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class CompanyViewModel : ViewModel(), KoinComponent {

    private val repository by inject<Repository>()

    val candlesListData = MutableLiveData<List<EntityCompany>>()
    private val candlesListDataCache90 = mutableListOf<EntityCompany>()
    private val candlesListDataCacheYear = mutableListOf<EntityCompany>()
    private val candlesListDataCacheFiveYear = mutableListOf<EntityCompany>()
    private val candlesListDataCacheAllPermission = mutableListOf<EntityCompany>()

    val errorData = MutableLiveData<String>()
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

    fun getCompanyCandles90(secId: String) {
        scope.launch {
            if (candlesListDataCache90.isEmpty() || candlesListDataCache90.first().secId != secId) {
                candlesListDataCache90.clear()
                val date = Date()
                val candlesList = repository.getCompanyCandles(secId, date.changeDay(-91), date)
                candlesListData.postValue(candlesList)
                candlesListDataCache90.addAll(candlesList)
            } else {
                candlesListData.postValue(candlesListDataCache90)
            }
        }
    }

    fun getCompanyCandlesYear(secId: String) {
        scope.launch {
            if (candlesListDataCacheYear.isEmpty() || candlesListDataCacheYear.first().secId != secId) {
                candlesListDataCacheYear.clear()
                val date = Date()
                val candlesList = repository.getCompanyCandles(secId, date.changeDay(-192), date)
                candlesListData.postValue(candlesList)
                candlesListDataCacheYear.addAll(candlesList)
            } else {
                candlesListData.postValue(candlesListDataCacheYear)
            }
        }
    }
    fun getCompanyCandles5Year(secId: String) {
        scope.launch {
            if (candlesListDataCacheFiveYear.isEmpty() || candlesListDataCacheFiveYear.first().secId != secId) {
                candlesListDataCacheFiveYear.clear()
                val date = Date()
                val candlesList = repository.getCompanyCandles(secId, date.changeDay(-1825), date)
                candlesListData.postValue(candlesList)
                candlesListDataCacheFiveYear.addAll(candlesList)
            } else {
                candlesListData.postValue(candlesListDataCacheFiveYear)
            }
        }
    }
    fun getCompanyCandlesAll(secId: String) {
        scope.launch {
            if (candlesListDataCacheAllPermission.isEmpty() || candlesListDataCacheAllPermission.first().secId != secId) {
                candlesListDataCacheAllPermission.clear()
                val date = Date()
                val candlesList = repository.getCompanyCandles(secId, date.changeDay(-7300), date)
                candlesListData.postValue(candlesList)
                candlesListDataCacheAllPermission.addAll(candlesList)
            } else {
                candlesListData.postValue(candlesListDataCacheAllPermission)
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}