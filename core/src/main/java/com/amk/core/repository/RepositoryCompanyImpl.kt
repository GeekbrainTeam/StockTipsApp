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
                        EntityCompany(
                            it.tradeData.convertToDate(),
                            it.shortName,
                            it.secId,
                            it.open,
                            it.low,
                            it.high,
                            it.close
                        )
                    },
                    listCompanyCacheAfterYesterday.map {
                        EntityCompany(
                            it.tradeData.convertToDate(),
                            it.shortName,
                            it.secId,
                            it.open,
                            it.low,
                            it.high,
                            it.close
                        )
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
                        EntityCompany(
                            it.tradeData.convertToDate(),
                            it.shortName,
                            it.secId,
                            it.open,
                            it.low,
                            it.high,
                            it.close
                        )
                    },
                    listCompanyCacheHalfYear.map {
                        EntityCompany(
                            it.tradeData.convertToDate(),
                            it.shortName,
                            it.secId,
                            it.open,
                            it.low,
                            it.high,
                            it.close
                        )
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
        network1: List<EntityCompany>,
        network2: List<EntityCompany>,
        network3: List<EntityCompany>
    ) {
        cacheRepository.deleteListCompanyOneDay()
        cacheRepository.deleteListCompanyAfterYesterday()
        cacheRepository.deleteListCompanyHalfYear()
        network1.forEach {
            cacheRepository.addCompanyOneDay(
                CacheCompanyOneDay(
                    secId = it.secId,
                    tradeData = it.tradeDate.convertToString(),
                    shortName = it.shortName,
                    open = it.open,
                    low = it.low,
                    high = it.high,
                    close = it.close
                )
            )
        }
        network2.forEach {
            cacheRepository.addCompanyAfterYesterday(
                CacheCompanyAfterYesterday(
                    secId = it.secId,
                    tradeData = it.tradeDate.convertToString(),
                    shortName = it.shortName,
                    open = it.open,
                    low = it.low,
                    high = it.high,
                    close = it.close
                )
            )
        }
        network3.forEach {
            cacheRepository.addCompanyHalfYear(
                CacheCompanyHalfYear(
                    secId = it.secId,
                    tradeData = it.tradeDate.convertToString(),
                    shortName = it.shortName,
                    open = it.open,
                    low = it.low,
                    high = it.high,
                    close = it.close
                )
            )
        }
    }
}

