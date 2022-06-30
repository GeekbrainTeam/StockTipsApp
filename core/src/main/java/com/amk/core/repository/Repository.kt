package com.amk.core.repository

import com.amk.core.entity.Company
import com.amk.core.retrofit.GsonCompaniesPageResponseStructure
import com.amk.core.retrofit.MoexApiImpl
import com.amk.core.retrofit.MoexApiService
import kotlinx.coroutines.*
import java.text.SimpleDateFormat

class Repository(
    private val activity: View,
    private val apiService: MoexApiService = MoexApiImpl().getMoexService()
) {

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

    fun getAllCompany() {
        scope.launch {
            val companiesList = mutableListOf<Company>()
            var index = 0L
            val pageSize = 100L
            var total: Long
            do {
                val response = apiService.getCompaniesPage(index)
                addList(response, companiesList)
                total = response.historyCursor.data[0][1]
                index += pageSize
            } while (index < total)

            activity.showResult(companiesList)
        }
    }

    private fun addList(
        response: GsonCompaniesPageResponseStructure,
        companiesList: MutableList<Company>
    ) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        response.history.data.forEach { companyInfo ->
            if (isContainsNulls(companyInfo)) {
                companiesList.add(
                    Company(
                        containsNulls = true,
                        tradeDate = sdf.parse(companyInfo[1] ?: "1970-1-1"),
                        shortName = companyInfo[2] ?: "empty",
                        secId = companyInfo[3] ?: "empty",
                    )
                )
            } else {
                companiesList.add(
                    Company(
                        containsNulls = false,
                        tradeDate = sdf.parse(companyInfo[1] ?: "1970-1-1"),
                        shortName = companyInfo[2] ?: "empty",
                        secId = companyInfo[3] ?: "empty",
                        open = companyInfo[6]?.toDouble() ?: 0.0,
                        low = companyInfo[7]?.toDouble() ?: 0.0,
                        high = companyInfo[8]?.toDouble() ?: 0.0,
                        close = companyInfo[9]?.toDouble() ?: 0.0
                    )
                )
            }
        }
    }

    private fun isContainsNulls(companyInfo: List<String?>) =
        companyInfo[6] == null || companyInfo[7] == null
                || companyInfo[8] == null || companyInfo[11] == null
}