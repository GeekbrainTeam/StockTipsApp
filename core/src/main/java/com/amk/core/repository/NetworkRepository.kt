package com.amk.core.repository

import com.amk.core.entity.EntityCompany
import com.amk.core.retrofit.GsonCompaniesPageResponseStructure
import com.amk.core.retrofit.MoexApiService
import com.amk.core.utils.changeDay
import com.amk.core.utils.convertToDate
import com.amk.core.utils.convertToString
import java.util.*

class NetworkRepository(private val apiService: MoexApiService) : Repository {

    private var currentDate : Date? = null

    override suspend fun getCompaniesLastDate(): List<EntityCompany> {
        val companiesList = mutableListOf<EntityCompany>()
        var index = 0L
        val pageSize = 100L

        var response = apiService.getCompaniesLastDatePage(start = index)
        while (response.history.data.isNotEmpty()) {
            addToList(response, companiesList)
            index += pageSize
            response = apiService.getCompaniesLastDatePage(start = index)
        }
        currentDate=companiesList.first().tradeDate
        return companiesList
    }

    override suspend fun getCompaniesAfterLastDate(): List<EntityCompany> {
        return currentDate?.let {
             getCompaniesByDate(it.changeDay(-1))
        }?: emptyList()
    }

    override suspend fun getCompaniesHalfYearDate(): List<EntityCompany> {
        return currentDate?.let {
            getCompaniesByDate(it.changeDay(-182))
        }?: emptyList()
    }

    override suspend fun getCompaniesByDate(date: Date): List<EntityCompany> {
        val companiesList = mutableListOf<EntityCompany>()
        var index = 0L
        val pageSize = 100L
        var numberOfConnections = 30
        var tryDate = date
        var response = apiService.getCompaniesByDatePage(date = tryDate.convertToString())
        while (response.history.data.isEmpty() && numberOfConnections > 0) {
            numberOfConnections--
            tryDate = tryDate.changeDay(-1)
            response = apiService.getCompaniesByDatePage(date = tryDate.convertToString())
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
    ): List<EntityCompany> {
        val companiesList = mutableListOf<EntityCompany>()
        var index = 0L
        val pageSize = 100L
        var response = apiService.getCompanyCandlesPage(
            secId = secId,
            dateFrom = dateFrom.convertToString(),
            dateTill = dateTill.convertToString(),
            start = index
        )
        while (response.history.data.isNotEmpty()) {
            addToList(response, companiesList)
            index += pageSize
            response = apiService.getCompanyCandlesPage(
                secId = secId,
                dateFrom = dateFrom.convertToString(),
                dateTill = dateTill.convertToString(),
                start = index
            )
        }
        return companiesList
    }


    private fun addToList(
        response: GsonCompaniesPageResponseStructure,
        companiesList: MutableList<EntityCompany>
    ) {
        response.history.data.forEach { companyInfo ->
            if (!isContainsNulls(companyInfo)) {
                companiesList.add(
                    EntityCompany(
                        tradeDate = companyInfo[1].convertToDate(),
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