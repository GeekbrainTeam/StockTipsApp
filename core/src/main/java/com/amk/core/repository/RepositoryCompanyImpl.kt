package com.amk.core.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.amk.core.entity.BaseCashCompany
import com.amk.core.entity.Company
import com.amk.core.entity.EntityCompany
import com.amk.core.entity.FavoriteCompany
import com.amk.core.interactors.CompanyFactory
import com.amk.core.utils.DATA_LOAD
import com.amk.core.utils.convertToDate
import com.amk.core.utils.convertToString
import java.util.*

class RepositoryCompanyImpl(
    context: Context,
    private val networkRepository: Repository,
    private val cacheRepository: CacheRepository
) : RepositoryCompany {

    private val sharedPref = context.getSharedPreferences(DATA_LOAD, MODE_PRIVATE)
    private val lastData = sharedPref.getString(DATA_LOAD, "")
    private val todayData = Date().convertToString()

    override suspend fun сreateListOneDayYesterday(): List<Company> {
        val listFavorite = cacheRepository.getFavoriteCompanies().map { it.secId }.toList()
        val dataGetReadyIsShow = mutableListOf<Company>()

        if (lastData == todayData) {
            getDataFromCash(
                dataGetReadyIsShow = dataGetReadyIsShow,
                listFavorite = listFavorite,
                listCompanyFromPreviousPeriod = cacheRepository.getCompanyAfterYesterday()
            )
        } else {
            getDataFromNetwork(dataGetReadyIsShow = dataGetReadyIsShow, listFavorite = listFavorite)
        }
        return dataGetReadyIsShow
    }

    override suspend fun сreateListOneDayHalfYear(): List<Company> {
        val listFavorite = cacheRepository.getFavoriteCompanies().map { it.secId }.toList()
        val dataGetReadyIsShow = mutableListOf<Company>()

        if (lastData == todayData) {
            getDataFromCash(
                dataGetReadyIsShow = dataGetReadyIsShow,
                listFavorite = listFavorite,
                listCompanyFromPreviousPeriod = cacheRepository.getCompanyHalfYear()
            )
        } else {
            getDataFromNetwork(dataGetReadyIsShow = dataGetReadyIsShow, listFavorite = listFavorite)
        }
        return dataGetReadyIsShow
    }

    private suspend fun getDataFromNetwork(
        dataGetReadyIsShow: MutableList<Company>,
        listFavorite: List<String>,
    ) {
        val listCompanyNetworkOneDay = networkRepository.getCompaniesLastDate()
        val listCompanyNetworkAfterYesterday = networkRepository.getCompaniesAfterLastDate()
        val listCompanyNetworkNetworkHalfYear = networkRepository.getCompaniesHalfYearDate()

        dataGetReadyIsShow.addAll(
            CompanyFactory(
                currentDateCompanies = listCompanyNetworkOneDay,
                previousDateCompanies = listCompanyNetworkAfterYesterday,
                listFavorite = listFavorite
            ).getCompanies()
        )
        addToCache(
            entityCompaniesOneDay = listCompanyNetworkOneDay,
            entityCompaniesAfterYesterday = listCompanyNetworkAfterYesterday,
            entityCompaniesHalfYear = listCompanyNetworkNetworkHalfYear
        )

        sharedPref.edit().putString(DATA_LOAD, todayData).apply()
    }

    private suspend fun getDataFromCash(
        dataGetReadyIsShow: MutableList<Company>,
        listFavorite: List<String>,
        listCompanyFromPreviousPeriod: List<BaseCashCompany>,
    ) {
        dataGetReadyIsShow.addAll(
            CompanyFactory(
                currentDateCompanies = cacheRepository.getCompanyOneDay()
                    .map { it.convertToEntityCompany() },
                previousDateCompanies = listCompanyFromPreviousPeriod.map { it.convertToEntityCompany() },
                listFavorite = listFavorite
            ).getCompanies()
        )
    }

    override suspend fun addFavoriteCompany(secId: String) {
        cacheRepository.addFavoriteCompany(FavoriteCompany(secId))
    }

    override suspend fun deleteFavoriteCompany(secId: String) {
        cacheRepository.deleteFavoriteCompany(secId)
    }

    private suspend fun addToCache(
        entityCompaniesOneDay: List<EntityCompany>,
        entityCompaniesAfterYesterday: List<EntityCompany>,
        entityCompaniesHalfYear: List<EntityCompany>
    ) {
        cacheRepository.clearCashCompany()
        cacheRepository.addCompanyOneDay(entityCompaniesOneDay)
        cacheRepository.addCompanyAfterYesterday(entityCompaniesAfterYesterday)
        cacheRepository.addCompanyHalfYear(entityCompaniesHalfYear)
    }

    private fun <T : BaseCashCompany> T.convertToEntityCompany(): EntityCompany =
        EntityCompany(
            tradeDate = tradeDate.convertToDate(),
            shortName = shortName,
            secId = secId,
            open = open,
            low = low,
            high = high,
            close = close
        )
}

