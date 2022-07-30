package com.amk.company.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amk.core.entity.EntityCompany
import com.amk.core.repository.Repository
import com.amk.core.utils.changeDay
import com.amk.core.utils.convertToDate
import com.amk.core.utils.daysListToMonthsList
import com.amk.core.utils.daysListToWeeksList
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class CompanyViewModel : ViewModel(), KoinComponent {

    private val repository by inject<Repository>()
    val candlesListData = MutableLiveData<List<EntityCompany>>()
    private val candlesListDataCache90 = mutableListOf<EntityCompany>()
    private val candlesListDataCacheHalfYear = mutableListOf<EntityCompany>()
    private val candlesListDataCacheYear = mutableListOf<EntityCompany>()
    private val candlesListDataCacheAll = mutableListOf<EntityCompany>()

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

    fun getCompanyCandlesHalfYear(secId: String) {
        scope.launch {
            if (candlesListDataCacheHalfYear.isEmpty() || candlesListDataCacheHalfYear.first().secId != secId) {
                candlesListDataCacheHalfYear.clear()
                val date = Date()
                val candlesList = repository.getCompanyCandles(secId, date.changeDay(-183), date)
                candlesListData.postValue(candlesList)
                candlesListDataCacheHalfYear.addAll(candlesList)
            } else {
                candlesListData.postValue(candlesListDataCacheHalfYear)
            }
        }
    }

    fun getCompanyCandlesYear(secId: String) {
        scope.launch {
            if (candlesListDataCacheYear.isEmpty() || candlesListDataCacheYear.first().secId != secId) {
                candlesListDataCacheYear.clear()
                val date = Date()
                val candlesList = repository.getCompanyCandles(secId, date.changeDay(-366), date)

                println("VM: list ${candlesList.size}")

                val weeksList = candlesList.daysListToWeeksList()
                candlesListData.postValue(weeksList)
                candlesListDataCacheYear.addAll(weeksList)
            } else {
                candlesListData.postValue(candlesListDataCacheYear)
            }
        }
    }

    fun getCompanyCandlesAll(secId: String) {
        scope.launch {
            if (candlesListDataCacheAll.isEmpty() || candlesListDataCacheAll.first().secId != secId) {
                candlesListDataCacheAll.clear()
                val date = Date()
                val candlesList =
                    repository.getCompanyCandles(secId, "1970-01-01".convertToDate(), date)
                val monthsList = candlesList.daysListToMonthsList()
                candlesListData.postValue(monthsList)
                candlesListDataCacheAll.addAll(monthsList)
            } else {
                candlesListData.postValue(candlesListDataCacheAll)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}