package com.amk.core.repository

import com.amk.core.db.CashDao
import com.amk.core.entity.*
import com.amk.core.utils.convertToString
import kotlinx.coroutines.flow.Flow

class CacheRepositoryRoom(private val cache: CashDao) : CacheRepository {

    override suspend fun clearCashCompany() {
        deleteListCompanyOneDay()
        deleteListCompanyAfterYesterday()
        deleteListCompanyHalfYear()
    }

    override suspend fun addCompanyOneDay(company: List<EntityCompany>) {
        company.forEach {
            cache.addCompanyOneDay(
                it.convertToCacheCompanyOneDay()
            )
        }
    }

    override suspend fun addCompanyAfterYesterday(company: List<EntityCompany>) {
        company.forEach {
            cache.addCompanyAfterYesterday(
                it.convertToCacheCompanyAfterYesterday()
            )
        }
    }

    override suspend fun addCompanyHalfYear(company: List<EntityCompany>) {
        company.forEach {
            cache.addCompanyHalfYear(
                it.convertToCacheCompanyHalfYear()
            )
        }
    }

    override suspend fun deleteListCompanyHalfYear() {
        cache.deleteListCompanyHalfYear()
    }

    override suspend fun deleteListCompanyOneDay() {
        cache.deleteListCompanyOneDay()
    }

    override suspend fun getCompanyOneDay(): List<CashCompanyOneDay> {
        return cache.getCompanyOneDay()
    }

    override suspend fun deleteListCompanyAfterYesterday() {
        cache.deleteListCompanyAfterYesterday()
    }

    override suspend fun getCompanyAfterYesterday(): List<CashCompanyAfterYesterday> {
        return cache.getCompanyAfterYesterday()
    }

    override suspend fun getCompanyHalfYear(): List<CashCompanyHalfYear> {
        return cache.getCompanyHalfYear()
    }

    override suspend fun addFavoriteCompany(company: EntityFavoriteCompany) {
        cache.addFavoriteCompany(company)
    }

    override suspend fun deleteFavoriteCompany(secId: String) {
        cache.deleteFavoriteCompany(secId)
    }

    override suspend fun getFavoriteCompanies(): Flow<List<EntityFavoriteCompany>> {
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