package com.amk.core.repository

import com.amk.core.entity.Company
import com.amk.core.utils.toDateU
import com.amk.core.retrofit.GsonCompaniesPageResponseStructure
import com.amk.core.retrofit.MoexApiService
import com.amk.core.utils.changeDayU
import com.amk.core.utils.toStringU
import java.util.*

class NetworkRepository(private val apiService: MoexApiService) : Repository {


    override suspend fun getCompaniesLastDate(): List<Company> {
        val companiesList = mutableListOf<Company>()
        var index = 0L
        val pageSize = 100L
        var response = apiService.getCompaniesLastDatePage(start = index)
        while (response.history.data.isNotEmpty()) {
            addToList(response, companiesList)
            index += pageSize
            response = apiService.getCompaniesLastDatePage(start = index)
        }
        return companiesList
    }

    override suspend fun getCompaniesByDate(date: Date): List<Company> {
        val companiesList = mutableListOf<Company>()
        var index = 0L
        val pageSize = 100L
        var numberOfConnections = 30
        var tryDate = date
        var response = apiService.getCompaniesByDatePage(date = tryDate.toStringU())
        while (response.history.data.isEmpty() && numberOfConnections > 0){
            numberOfConnections++
            tryDate.changeDayU(-1)
            response = apiService.getCompaniesByDatePage(date = tryDate.toStringU())
        }
        while (response.history.data.isNotEmpty()) {
            addToList(response, companiesList)
            index += pageSize
            response = apiService.getCompaniesLastDatePage(start = index)
        }
        return companiesList
    }

    override suspend fun getCompanyCandles(
        secId: String,
        dateFrom: Date,
        dateTill: Date
    ): List<Company> {
        val companiesList = mutableListOf<Company>()
        var index = 0L
        val pageSize = 100L
        var response = apiService.getCompanyCandlesPage(
            secId = secId,
            dateFrom = dateFrom.toStringU(),
            dateTill = dateTill.toStringU(),
            start = index
        )
        while (response.history.data.isNotEmpty()) {
            addToList(response, companiesList)
            index += pageSize
            response = apiService.getCompanyCandlesPage(
                secId = secId,
                dateFrom = dateFrom.toStringU(),
                dateTill = dateTill.toStringU(),
                start = index
            )
        }
        return companiesList
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