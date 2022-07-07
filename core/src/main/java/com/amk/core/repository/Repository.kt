package com.amk.core.repository

import com.amk.core.entity.Company
import com.amk.core.entity.toDateU
import com.amk.core.retrofit.GsonCompaniesPageResponseStructure
import com.amk.core.retrofit.MoexApiImpl
import com.amk.core.retrofit.MoexApiService
import kotlinx.coroutines.*

class Repository(
    private val activity: View,
    private val apiService: MoexApiService = MoexApiImpl().getMoexService()
) {
    private var job: Job? = null
    private val scope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private fun handleError(error: Throwable) {
        activity.showError(error.toString())
    }

    fun getCompanies() {
        job?.cancel()
        job = scope.launch {
            val companiesList = mutableListOf<Company>()
            var index = 0L
            val pageSize = 100L
            var response = apiService.getCompaniesPage(start = index)
            while (response.history.data.isNotEmpty()) {
                addToList(response, companiesList)
                index += pageSize
                response = apiService.getCompaniesPage(start = index)
            }
            activity.showResult(companiesList)
        }
    }

    fun getCompanyCandles(secId: String) {
        job?.cancel()
        job = scope.launch {
            val companiesList = mutableListOf<Company>()
            var index = 0L
            val pageSize = 100L
            var response = apiService.getCompanyCandlesPage(secId = secId, start = index)
            while (response.history.data.isNotEmpty()) {
                addToList(response, companiesList)
                index += pageSize
                response = apiService.getCompanyCandlesPage(secId = secId, start = index)
            }
            activity.showResult(companiesList)
        }
    }

    private fun addToList(
        response: GsonCompaniesPageResponseStructure,
        companiesList: MutableList<Company>
    ) {
        response.history.data.forEach { companyInfo ->
            if (isContainsNulls(companyInfo)) {
                companiesList.add(
                    Company(
                        containsNulls = true,
                        tradeDate = companyInfo[1].toDateU(),
                        shortName = companyInfo[2],
                        secId = companyInfo[3]
                    )
                )
            } else {
                companiesList.add(
                    Company(
                        containsNulls = false,
                        tradeDate = companyInfo[1].toDateU(),
                        shortName = companyInfo[2],
                        secId = companyInfo[3],
                        open = companyInfo[6].toDouble(),
                        low = companyInfo[7].toDouble(),
                        high = companyInfo[8].toDouble(),
                        close = companyInfo[9].toDouble()
                    )
                )
            }
        }
    }

    private fun isContainsNulls(companyInfo: List<String?>) =
        companyInfo[6] == null || companyInfo[7] == null
                || companyInfo[8] == null || companyInfo[11] == null
}