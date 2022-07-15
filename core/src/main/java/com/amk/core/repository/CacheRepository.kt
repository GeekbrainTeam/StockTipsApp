package com.amk.core.repository

import com.amk.core.db.ChacheDao
import com.amk.core.entity.CacheCompanyAfterYesterday
import com.amk.core.entity.CacheCompanyHalfYear
import com.amk.core.entity.CacheCompanyOneDay
import com.amk.core.entity.FavoriteCompany

class CacheRepository(private val cache: ChacheDao) {

    suspend fun addCompanyOneDay(company: CacheCompanyOneDay) {
        cache.addCompanyOneDay(company)
    }

    suspend fun deleteListCompanyOneDay() {
        cache.deleteListCompanyOneDay()
    }

    suspend fun getCompanyOneDay(): List<CacheCompanyOneDay> {
        return cache.getCompanyOneDay()
    }

    suspend fun addCompanyAfterYesterday(company: CacheCompanyAfterYesterday) {
        cache.addCompanyAfterYesterday(company)
    }

    suspend fun deleteListCompanyAfterYesterday() {
        cache.deleteListCompanyAfterYesterday()
    }

    suspend fun getCompanyAfterYesterday(): List<CacheCompanyAfterYesterday> {
        return cache.getCompanyAfterYesterday()
    }

    suspend fun addCompanyHalfYear(company: CacheCompanyHalfYear) {
        cache.addCompanyHalfYear(company)
    }

    suspend fun deleteListCompanyHalfYear() {
        cache.deleteListCompanyHalfYear()
    }

    suspend fun getCompanyHalfYear(): List<CacheCompanyHalfYear> {
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