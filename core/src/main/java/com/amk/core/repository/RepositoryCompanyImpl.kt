package com.amk.core.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.amk.core.entity.*
import com.amk.core.interactors.CompanyFactory
import com.amk.core.utils.DATA_LOAD
import com.amk.core.utils.convertToDate
import com.amk.core.utils.convertToString
import java.util.*

class RepositoryCompanyImpl(
    context: Context,
    private val networkRepository: NetworkRepository,
    private val cacheRepository: CacheRepository
) : RepositoryCompany {
    private val sharedPref =
        context.getSharedPreferences(DATA_LOAD, MODE_PRIVATE)
    private val lastData = sharedPref.getString(DATA_LOAD, "")
    private val todayData = Date().convertToString()

    override suspend fun CreateListOneDayYesterday(): List<Company> {
        val listFavorite = cacheRepository.getCompanyFavoriteCompany().map { it.secId }.toList()
        val dataGetReadyIsShow = mutableListOf<Company>()
        if (lastData == todayData) {
            val listCompanyCacheOneDay = cacheRepository.getCompanyOneDay()
            val listCompanyCacheAfterYesterday = cacheRepository.getCompanyAfterYesterday()
            dataGetReadyIsShow.addAll(
                CompanyFactory(
                    listCompanyCacheOneDay.map {
                        it.convertToEntityCompany()
                    },
                    listCompanyCacheAfterYesterday.map {
                        it.convertToEntityCompany()
                    },
                    listFavorite
                ).getCompanies()
            )
        } else {
            val listCompanyNetworkOneDay = networkRepository.getCompaniesLastDate()
            val listCompanyNetworkAfterYesterday = networkRepository.getCompaniesAfterLastDate()
            val listCompanyNetworkNetworkHalfYear = networkRepository.getCompaniesHalfYearDate()
            dataGetReadyIsShow.addAll(
                CompanyFactory(
                    listCompanyNetworkOneDay,
                    listCompanyNetworkAfterYesterday,
                    listFavorite
                ).getCompanies()
            )
            addToCache(
                listCompanyNetworkOneDay,
                listCompanyNetworkAfterYesterday,
                listCompanyNetworkNetworkHalfYear
            )

            sharedPref.edit().putString(DATA_LOAD, todayData).apply()
        }
        return dataGetReadyIsShow
    }

    override suspend fun CreateListOneDayHalfYear(): List<Company> {
        val listFavorite = cacheRepository.getCompanyFavoriteCompany().map { it.secId }.toList()
        val dataGetReadyIsShow = mutableListOf<Company>()
        if (lastData == todayData) {
            val listCompanyCacheOneDay = cacheRepository.getCompanyOneDay()
            val listCompanyCacheHalfYear = cacheRepository.getCompanyHalfYear()
            dataGetReadyIsShow.addAll(
                CompanyFactory(
                    listCompanyCacheOneDay.map {
                        it.convertToEntityCompany()
                    },
                    listCompanyCacheHalfYear.map {
                        it.convertToEntityCompany()
                    },
                    listFavorite
                ).getCompanies()
            )
        } else {
            val listCompanyNetworkOneDay = networkRepository.getCompaniesLastDate()
            val listCompanyNetworkAfterYesterday = networkRepository.getCompaniesAfterLastDate()
            val listCompanyNetworkNetworkHalfYear = networkRepository.getCompaniesHalfYearDate()
            dataGetReadyIsShow.addAll(
                CompanyFactory(
                    listCompanyNetworkOneDay,
                    listCompanyNetworkNetworkHalfYear,
                    listFavorite
                ).getCompanies()
            )
            addToCache(
                listCompanyNetworkOneDay,
                listCompanyNetworkAfterYesterday,
                listCompanyNetworkNetworkHalfYear
            )
            sharedPref.edit().putString(DATA_LOAD, todayData).apply()
        }
        return dataGetReadyIsShow
    }

    private suspend fun addToCache(
        entityCompaniesOneDay: List<EntityCompany>,
        entityCompaniesAfterYesterday: List<EntityCompany>,
        entityCompaniesHalfYear: List<EntityCompany>
    ) {
        cacheRepository.deleteListCompanyOneDay()
        cacheRepository.deleteListCompanyAfterYesterday()
        cacheRepository.deleteListCompanyHalfYear()
        entityCompaniesOneDay.forEach {
            cacheRepository.addCompanyOneDay(
                it.convertToCacheCompanyOneDay()
            )
        }
        entityCompaniesAfterYesterday.forEach {
            cacheRepository.addCompanyAfterYesterday(
                it.convertToCacheCompanyAfterYesterday()
            )
        }
        entityCompaniesHalfYear.forEach {
            cacheRepository.addCompanyHalfYear(
                it.convertToCacheCompanyHalfYear()
            )
        }
    }

    private fun CacheCompanyAfterYesterday.convertToEntityCompany(): EntityCompany =
        EntityCompany(
            tradeData.convertToDate(),
            shortName,
            secId,
            open,
            low,
            high,
            close
        )

    private fun CacheCompanyOneDay.convertToEntityCompany(): EntityCompany =
        EntityCompany(
            tradeData.convertToDate(),
            shortName,
            secId,
            open,
            low,
            high,
            close
        )

    private fun CacheCompanyHalfYear.convertToEntityCompany(): EntityCompany =
        EntityCompany(
            tradeData.convertToDate(),
            shortName,
            secId,
            open,
            low,
            high,
            close
        )

    private fun EntityCompany.convertToCacheCompanyAfterYesterday(): CacheCompanyAfterYesterday =
        CacheCompanyAfterYesterday(
            secId,
            tradeDate.convertToString(),
            shortName,
            open,
            low,
            high,
            close
        )

    private fun EntityCompany.convertToCacheCompanyOneDay(): CacheCompanyOneDay =
        CacheCompanyOneDay(
            secId,
            tradeDate.convertToString(),
            shortName,
            open,
            low,
            high,
            close
        )

    private fun EntityCompany.convertToCacheCompanyHalfYear(): CacheCompanyHalfYear =
        CacheCompanyHalfYear(
            secId,
            tradeDate.convertToString(),
            shortName,
            open,
            low,
            high,
            close
        )


}

