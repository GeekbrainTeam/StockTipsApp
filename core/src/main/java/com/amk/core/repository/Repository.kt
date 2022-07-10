package com.amk.core.repository

import com.amk.core.entity.Company
import java.util.*

interface Repository {
    suspend fun getCompaniesLastDate(): List<Company>
    suspend fun getCompaniesByDate(date: Date): List<Company>
    suspend fun getCompanyCandles(secId: String, dateFrom: Date, dateTill: Date): List<Company>
}