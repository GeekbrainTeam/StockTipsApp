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

    fun getCompanyCandles(secId: String) {
        job?.cancel()
        job = scope.launch {
            val date = Date()
            val candlesList = repository.getCompanyCandles(secId, date.changeDay(-90), date)
            candlesListData.postValue(candlesList)
        }
    }


    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}