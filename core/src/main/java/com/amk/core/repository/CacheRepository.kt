package com.amk.core.repository

import com.amk.core.db.CashDao
import com.amk.core.entity.CashCompanyAfterYesterday
import com.amk.core.entity.CashCompanyHalfYear
import com.amk.core.entity.CashCompanyOneDay
import com.amk.core.entity.FavoriteCompany

class CacheRepository(private val cache: CashDao)  {

    suspend fun addCompanyOneDay(company: CashCompanyOneDay) {
        cache.addCompanyOneDay(company)
    }

    suspend fun deleteListCompanyOneDay() {
        cache.deleteListCompanyOneDay()
    }

    suspend fun getCompanyOneDay(): List<CashCompanyOneDay> {
        return cache.getCompanyOneDay()
    }

    suspend fun addCompanyAfterYesterday(company: CashCompanyAfterYesterday) {
        cache.addCompanyAfterYesterday(company)
    }

    suspend fun deleteListCompanyAfterYesterday() {
        cache.deleteListCompanyAfterYesterday()
    }

    suspend fun getCompanyAfterYesterday(): List<CashCompanyAfterYesterday> {
        return cache.getCompanyAfterYesterday()
    }

    suspend fun addCompanyHalfYear(company: CashCompanyHalfYear) {
        cache.addCompanyHalfYear(company)
    }

    suspend fun deleteListCompanyHalfYear() {
        cache.deleteListCompanyHalfYear()
    }

    suspend fun getCompanyHalfYear(): List<CashCompanyHalfYear> {
        return cache.getCompanyHalfYear()
    }

    suspend fun addCompany(company: FavoriteCompany) {
        cache.addCompany(company)
    }

    suspend fun deleteFavoriteCompany(secId: String) {
        cache.deleteFavoriteCompany(secId)
    }

    suspend fun getCompanyFavoriteCompany(): List<FavoriteCompany> {
        return cache.getCompanyFavoriteCompany()
    }

}