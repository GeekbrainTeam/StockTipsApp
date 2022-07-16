package com.amk.core.repository

import com.amk.core.db.CashDao
import com.amk.core.entity.*
import com.amk.core.utils.convertToString

class CacheRepository(private val cache: CashDao) {

    suspend fun clearCashCompany() {
        deleteListCompanyOneDay()
        deleteListCompanyAfterYesterday()
        deleteListCompanyHalfYear()
    }

    suspend fun addCompanyOneDay(company: List<EntityCompany>) {
        company.forEach {
            cache.addCompanyOneDay(
                it.convertToCacheCompanyOneDay()
            )
        }
    }

    suspend fun addCompanyAfterYesterday(company: List<EntityCompany>) {
        company.forEach {
            cache.addCompanyAfterYesterday(
                it.convertToCacheCompanyAfterYesterday()
            )
        }
    }

    suspend fun addCompanyHalfYear(company: List<EntityCompany>) {
        company.forEach {
            cache.addCompanyHalfYear(
                it.convertToCacheCompanyHalfYear()
            )
        }
    }

    suspend fun deleteListCompanyHalfYear() {
        cache.deleteListCompanyHalfYear()
    }

    suspend fun deleteListCompanyOneDay() {
        cache.deleteListCompanyOneDay()
    }

    suspend fun getCompanyOneDay(): List<CashCompanyOneDay> {
        return cache.getCompanyOneDay()
    }

    suspend fun deleteListCompanyAfterYesterday() {
        cache.deleteListCompanyAfterYesterday()
    }

    suspend fun getCompanyAfterYesterday(): List<CashCompanyAfterYesterday> {
        return cache.getCompanyAfterYesterday()
    }

    suspend fun getCompanyHalfYear(): List<CashCompanyHalfYear> {
        return cache.getCompanyHalfYear()
    }

    suspend fun addFavoriteCompany(company: FavoriteCompany) {
        cache.addFavoriteCompany(company)
    }

    suspend fun deleteFavoriteCompany(secId: String) {
        cache.deleteFavoriteCompany(secId)
    }

    suspend fun getFavoriteCompanies(): List<FavoriteCompany> {
        return cache.getFavoriteCompanies()
    }

    private fun EntityCompany.convertToCacheCompanyAfterYesterday(): CashCompanyAfterYesterday =
        CashCompanyAfterYesterday(
            secId,
            tradeDate.convertToString(),
            shortName,
            open,
            low,
            high,
            close
        )

    private fun EntityCompany.convertToCacheCompanyOneDay(): CashCompanyOneDay =
        CashCompanyOneDay(
            secId,
            tradeDate.convertToString(),
            shortName,
            open,
            low,
            high,
            close
        )

    private fun EntityCompany.convertToCacheCompanyHalfYear(): CashCompanyHalfYear =
        CashCompanyHalfYear(
            secId,
            tradeDate.convertToString(),
            shortName,
            open,
            low,
            high,
            close
        )
}