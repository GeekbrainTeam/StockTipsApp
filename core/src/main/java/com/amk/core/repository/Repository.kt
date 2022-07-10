package com.amk.core.repository

import com.amk.core.entity.EntityCompany
import java.util.*

interface Repository {
    suspend fun getCompaniesLastDate(): List<EntityCompany>
    suspend fun getCompaniesByDate(date: Date): List<EntityCompany>
    suspend fun getCompanyCandles(
        secId: String,
        dateFrom: Date,
        dateTill: Date
    ): List<EntityCompany>
}