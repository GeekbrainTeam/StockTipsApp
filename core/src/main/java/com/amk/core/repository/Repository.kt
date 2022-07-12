package com.amk.core.repository

import com.amk.core.entity.EntityCompany
import java.util.*

interface Repository {
    fun getCompaniesLastDate(): List<EntityCompany>
    fun getCompaniesAfterLastDate(): List<EntityCompany>
    fun getCompaniesHalfYearDate(): List<EntityCompany>
    fun getCompaniesByDate(date: Date): List<EntityCompany>
    fun getCompanyCandles(
        secId: String,
        dateFrom: Date,
        dateTill: Date
    ): List<EntityCompany>
}