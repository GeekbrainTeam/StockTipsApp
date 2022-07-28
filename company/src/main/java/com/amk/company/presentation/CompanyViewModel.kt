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
            val date = Date()
            val candlesList = repository.getCompanyCandles(secId, date.changeDay(-91), date)
            candlesListData.postValue(candlesList)
        }
    }
    fun getCompanyCandlesHalfYear(secId: String) {
        scope.launch {
            val date = Date()
            val candlesList = repository.getCompanyCandles(secId, date.changeDay(-182), date)
            candlesListData.postValue(candlesList)
        }
    }
    fun getCompanyCandles5Year(secId: String) {
        scope.launch {
            val date = Date()
            val candlesList = repository.getCompanyCandles(secId, date.changeDay(-1825), date)
            candlesListData.postValue(candlesList)
        }
    }
    fun getCompanyCandlesAll(secId: String) {
        scope.launch {
            val date = Date()
            val candlesList = repository.getCompanyCandles(secId, date.changeDay(-7300), date)
            candlesListData.postValue(candlesList)
        }
    }



    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}