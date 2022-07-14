package com.amk.core.repository

import com.amk.core.db.ChacheDao
import com.amk.core.entity.CacheCompanyAfterYesterday
import com.amk.core.entity.CacheCompanyHalfYear
import com.amk.core.entity.CacheCompanyOneDay
import com.amk.core.entity.FavoriteCompany

class CacheRepository(private val cache: ChacheDao) : ChacheDao {

    override suspend fun addCompanyOneDay(company: CacheCompanyOneDay) {
        cache.addCompanyOneDay(company)
    }

    override suspend fun deleteListCompanyOneDay() {
        cache.deleteListCompanyOneDay()
    }

    override suspend fun getCompanyOneDay(): List<CacheCompanyOneDay> {
        return cache.getCompanyOneDay()
    }

    override suspend fun addCompanyAfterYesterday(company: CacheCompanyAfterYesterday) {
        cache.addCompanyAfterYesterday(company)
    }

    override suspend fun deleteListCompanyAfterYesterday() {
        cache.deleteListCompanyAfterYesterday()
    }

    override suspend fun getCompanyAfterYesterday(): List<CacheCompanyAfterYesterday> {
        return cache.getCompanyAfterYesterday()
    }

    override suspend fun addCompanyHalfYear(company: CacheCompanyHalfYear) {
        cache.addCompanyHalfYear(company)
    }

    override suspend fun deleteListCompanyHalfYear() {
        cache.deleteListCompanyHalfYear()
    }

    override suspend fun getCompanyHalfYear(): List<CacheCompanyHalfYear> {
        return cache.getCompanyHalfYear()
    }

    override suspend fun addCompany(company: FavoriteCompany) {
        cache.addCompany(company)
    }

    override suspend fun deleteFavoriteCompany(secId: String) {
        cache.deleteFavoriteCompany(secId)
    }

    override suspend fun getCompanyFavoriteCompany(): List<FavoriteCompany> {
        return cache.getCompanyFavoriteCompany()
    }

}