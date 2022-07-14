package com.amk.core.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.amk.core.entity.*
import com.amk.core.interactors.CompanyFactory
import com.amk.core.utils.DATA_HALF_YEAR
import com.amk.core.utils.DATA_ONE_TWO
import com.amk.core.utils.convertToDate
import com.amk.core.utils.convertToString
import java.util.*

class RepositoryCompanyImpl(
    private val context: Context,
    private val networkRepository: NetworkRepository,
    private val cacheRepository: CacheRepository
) : RepositoryCompany {
    private val sharedPref1 =
        context.getSharedPreferences(DATA_ONE_TWO, MODE_PRIVATE)
    private val sharedPref2 =
        context.getSharedPreferences(DATA_HALF_YEAR, MODE_PRIVATE)

    private val lastDataOneTwo = sharedPref1.getString(DATA_ONE_TWO, "")
    private val lastDataHalfYear = sharedPref2.getString(DATA_HALF_YEAR, "")
    private val todayData = Date().convertToString()

    override suspend fun CreateListOneDayYesterday(): List<Company> {
        val listFavorite = cacheRepository.getCompanyFavoriteCompany().map { it.secId }.toList()
        val dataGetReadyIsShow = mutableListOf<Company>()
        if (lastDataOneTwo == todayData) {
            val listCompanyCacheOneDay = cacheRepository.getCompanyOneDay()
            val listCompanyCacheAfterYesterday = cacheRepository.getCompanyAfterYesterday()
            //cacheToResult(currentList = tmpListFromPrevious, companiesResult = dataGetReadyIsShow)
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
            clearCache()
            val listCompanyNetworkOneDay = networkRepository.getCompaniesLastDate()
            val listCompanyNetworkAfterYesterday = networkRepository.getCompaniesAfterLastDate()
            dataGetReadyIsShow.addAll(
                CompanyFactory(
                    listCompanyNetworkOneDay,
                    listCompanyNetworkAfterYesterday,
                    listFavorite
                ).getCompanies()
            )
//            tempList.forEach { companyLast ->
//                val currentCompany =
//                    listCompanyNetworkAfterYesterday.first { it.shortName == companyLast.shortName }
//                dataGetReadyIsShow.add(
//                    Company(
//                        shortName = currentCompany.shortName,
//                        entityCompany = companyLast,
//                        currentCompany.close - companyLast.close,
//                        100 - currentCompany.close / companyLast.close * 100,
//                        listFavorite.contains(FavoriteCompany(currentCompany.secId))
//                    )
//                )
//            }
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
        }
        sharedPref1.edit().putString(DATA_ONE_TWO, todayData).apply()
        return dataGetReadyIsShow
    }

    override suspend fun CreateListOneDayHalfYear(): List<Company> {
        val listCompanyCacheOneDay = cacheRepository.getCompanyOneDay()
        val listCompanyCacheHalfYear = cacheRepository.getCompanyHalfYear()
        val listFavorite = cacheRepository.getCompanyFavoriteCompany()
        val dataGetReadyIsShow = mutableListOf<Company>()
        if (lastDataHalfYear == todayData) {
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
            val tempList = listCompanyCacheOneDay.intersect(tmpListFromPrevious).toList()
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
                dataGetReadyIsShow.add(
                    Company(
                        shortName = currentCompany.shortName,
                        entityCompany = currentCompanyToCache,
                        currentCompany.close - companyLast.close,
                        100 - currentCompany.close / companyLast.close * 100,
                        listFavorite.contains(FavoriteCompany(currentCompany.secId))
                    )
                )
            }
        } else {
            clearCache()
            val listCompanyNetworkOneDay = networkRepository.getCompaniesLastDate()
            val listCompanyNetworkNetworkHalfYear = networkRepository.getCompaniesHalfYearDate()
            val tempList =
                listCompanyNetworkOneDay.intersect(listCompanyNetworkNetworkHalfYear).toList()
            tempList.forEach { companyLast ->
                val currentCompany =
                    listCompanyNetworkNetworkHalfYear.first { it.shortName == companyLast.shortName }
                dataGetReadyIsShow.add(
                    Company(
                        shortName = currentCompany.shortName,
                        entityCompany = companyLast,
                        currentCompany.close - companyLast.close,
                        100 - currentCompany.close / companyLast.close * 100,
                        listFavorite.contains(FavoriteCompany(currentCompany.secId))
                    )
                )
            }
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
            sharedPref2.edit().putString(DATA_HALF_YEAR, todayData).apply()
        }
        return dataGetReadyIsShow
    }


    private suspend fun clearCache() {
        cacheRepository.deleteListCompanyOneDay()
        cacheRepository.deleteListCompanyAfterYesterday()
        cacheRepository.deleteListCompanyHalfYear()
    }

}

