package com.amk.core.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.amk.core.entity.*
import com.amk.core.utils.STORAGE_NAME
import com.amk.core.utils.convertToDate
import com.amk.core.utils.convertToString
import java.util.*

class RepositoryCompanyImpl(
    private val context: Context,
    private val networkRepository: NetworkRepository,
    private val cacheRepository: CacheRepsitory
) {
    private val listCompanyNetworkOneDay = networkRepository.getCompaniesLastDate()
    private val listCompanyNetworkAfterYesterday = networkRepository.getCompaniesAfterLastDate()
    private val listCompanyNetworkNetworkHalfYear = networkRepository.getCompaniesHalfYearDate()
    private val listCompanyCacheOneDay = cacheRepository.getCompanyOneDay()
    private val listCompanyCacheAfterYesterday = cacheRepository.getCompanyAfterYesterday()
    private val listCompanyCacheHalfYear = cacheRepository.getCompanyHalfYear()
    private val listFavorite = cacheRepository.getCompanyFavoriteCompany()
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(STORAGE_NAME, MODE_PRIVATE)
    private val lastData = sharedPreferences.getString(STORAGE_NAME, "")
    private val todayData = Date().convertToString()

    suspend fun CreateListOneDayYesterday(): List<Company> {
        val dataGetReadyIsShow = mutableListOf<Company>()
        if (lastData == todayData) {
            val tmpListFromPrevious = mutableListOf<CacheCompanyOneDay>()
            listCompanyCacheAfterYesterday.forEach {
                tmpListFromPrevious.add(
                    CacheCompanyOneDay(
                        secId = it.secId,
                        tradeData = it.tradeData,
                        shortName = it.shortName,
                        open = it.open,
                        low = it.low,
                        high = it.high,
                        close = it.close
                    )
                )
            }
            cacheToResult(currentList = tmpListFromPrevious, companiesResult = dataGetReadyIsShow)

        } else {
            clearCache()
            dataCacheNotDataToday(
                companiesToday = listCompanyNetworkOneDay,
                companiesLast = listCompanyNetworkAfterYesterday,
                companiesResult = dataGetReadyIsShow
            )
        }
        return dataGetReadyIsShow
    }

    suspend fun CreateListOneDayHalfYear(): List<Company> {
        val dataGetReadyIsShow = mutableListOf<Company>()
        if (lastData == todayData) {
            val tmpListFromPrevious = mutableListOf<CacheCompanyOneDay>()
            listCompanyCacheHalfYear.forEach {
                tmpListFromPrevious.add(
                    CacheCompanyOneDay(
                        secId = it.secId,
                        tradeData = it.tradeData,
                        shortName = it.shortName,
                        open = it.open,
                        low = it.low,
                        high = it.high,
                        close = it.close
                    )
                )
            }

            cacheToResult(currentList = tmpListFromPrevious, companiesResult = dataGetReadyIsShow)

        } else {
            clearCache()
            dataCacheNotDataToday(
                companiesToday = listCompanyNetworkOneDay,
                companiesLast = listCompanyNetworkNetworkHalfYear,
                companiesResult = dataGetReadyIsShow
            )
            addToListsCache()
            sharedPreferences.edit().putString(STORAGE_NAME, todayData).apply()
        }
        return dataGetReadyIsShow
    }

    private suspend fun addToListsCache() {
        listCompanyNetworkOneDay.forEach {
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
        listCompanyNetworkAfterYesterday.forEach {
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
        listCompanyNetworkNetworkHalfYear.forEach {
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

    private suspend fun clearCache() {
        cacheRepository.deleteListCompanyOneDay()
        cacheRepository.deleteListCompanyAfterYesterday()
        cacheRepository.deleteListCompanyHalfYear()
    }

    private fun cacheToResult(
        currentList: MutableList<CacheCompanyOneDay>,
        companiesResult: MutableList<Company>
    ) {
        val tempList = listCompanyCacheOneDay.intersect(currentList).toList()
        tempList.forEach { companyLast ->
            val currentCompany =
                listCompanyCacheOneDay.first { it.shortName == companyLast.shortName }
            val currentCompanyToCache = EntityCompany(
                tradeDate = currentCompany.tradeData.convertToDate(),
                secId = currentCompany.secId,
                shortName = currentCompany.shortName,
                open = currentCompany.open,
                low = currentCompany.low,
                high = currentCompany.high,
                close = currentCompany.close
            )
            companiesResult.add(
                Company(
                    shortName = currentCompany.shortName,
                    entityCompany = currentCompanyToCache,
                    currentCompany.close - companyLast.close,
                    100 - currentCompany.close / companyLast.close * 100,
                    listFavorite.contains(FavoriteCompany(currentCompany.secId))
                )
            )
        }
    }

    private fun dataCacheNotDataToday(
        companiesToday: List<EntityCompany>,
        companiesLast: List<EntityCompany>,
        companiesResult: MutableList<Company>
    ): MutableList<Company> {
        val tempList =
            companiesToday.intersect(companiesLast).toList()
        tempList.forEach { companyLast ->
            val currentCompany =
                companiesLast.first { it.shortName == companyLast.shortName }
            companiesResult.add(
                Company(
                    shortName = currentCompany.shortName,
                    entityCompany = companyLast,
                    currentCompany.close - companyLast.close,
                    100 - currentCompany.close / companyLast.close * 100,
                    listFavorite.contains(FavoriteCompany(currentCompany.secId))
                )
            )
        }
        return companiesResult
    }
}

