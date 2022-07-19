package com.amk.company.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amk.company.getinfo.CompanyInfo
import com.amk.company.getinfo.InfoRepository
import com.amk.company.getinfo.retrofit.AlphavantageCoApiImpl
import com.amk.company.getinfo.retrofit.BankiRuApiImpl
import com.amk.core.entity.EntityCompany
import com.amk.core.repository.Repository
import com.amk.core.utils.changeDay
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class CompanyViewModel : ViewModel(), KoinComponent {

    private val repository by inject<Repository>()
    private var infoRepository = InfoRepository(
        repository,
        BankiRuApiImpl().getBankiRuApiService(),
        AlphavantageCoApiImpl().getAlphavantageCoService()
    )

    val candlesListData = MutableLiveData<List<EntityCompany>>()
    val companyInfoData = MutableLiveData<CompanyInfo>()
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

    fun getCompanyCandles(secId: String) {
        scope.launch {
            val date = Date()
            val candlesList = repository.getCompanyCandles(secId, date.changeDay(-90), date)
            candlesListData.postValue(candlesList)
        }
    }

    fun getCompanyInfo(secId: String) {
        scope.launch {
            val companyInfo = infoRepository.getCompanyInfo(secId)
            if (companyInfo.description.startsWith("error")) {
                if (companyInfo.description == "error parse") {
                    errorData.postValue("Parsing error")
                    companyInfoData.postValue(CompanyInfo("Parsing error", "no image"))
                } else {
                    errorData.postValue(companyInfo.imageURL)
                    companyInfoData.postValue(CompanyInfo(companyInfo.imageURL, "no image"))
                }
            }
            companyInfoData.postValue(companyInfo)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}