package com.amk.core.repository

import com.amk.core.entity.*
import kotlinx.coroutines.flow.Flow

interface CacheRepository {
    suspend fun clearCashCompany()

    suspend fun addCompanyOneDay(company: List<EntityCompany>)

    suspend fun addCompanyAfterYesterday(company: List<EntityCompany>)

    suspend fun addCompanyHalfYear(company: List<EntityCompany>)

    suspend fun deleteListCompanyHalfYear()

    suspend fun deleteListCompanyOneDay()

    suspend fun getCompanyOneDay(): List<CashCompanyOneDay>

    suspend fun deleteListCompanyAfterYesterday()

    suspend fun getCompanyAfterYesterday(): List<CashCompanyAfterYesterday>

    suspend fun getCompanyHalfYear(): List<CashCompanyHalfYear>

    suspend fun addFavoriteCompany(company: EntityFavoriteCompany)

    suspend fun deleteFavoriteCompany(secId: String)

    suspend fun getFavoriteCompanies(): Flow<List<EntityFavoriteCompany>>
}