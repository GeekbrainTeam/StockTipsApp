package com.amk.core.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.amk.core.entity.*
import com.amk.core.interactors.CompanyFactory
import com.amk.core.interactors.FavoriteFactory
import com.amk.core.utils.DATA_LOAD
import com.amk.core.utils.changeDay
import com.amk.core.utils.convertToDate
import com.amk.core.utils.convertToString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class RepositoryCompanyImpl(
    context: Context,
    private val networkRepository: Repository,
    private val cacheRepository: CacheRepository
) : RepositoryCompany {

    private val sharedPref = context.getSharedPreferences(DATA_LOAD, MODE_PRIVATE)
    private val lastData = sharedPref.getString(DATA_LOAD, "")
    private val todayData = Date().convertToString()

    override suspend fun createListOneDayYesterday(): Flow<List<Company>> {
        return cacheRepository.getFavoriteCompanies().map { favoriteCompanies ->
            val listFavorite = favoriteCompanies.map { it.secId }.toList()
            val dataGetReadyIsShow = mutableListOf<Company>()

            if (lastData == todayData) {
                getDataFromCash(
                    dataGetReadyIsShow = dataGetReadyIsShow,
                    listFavorite = listFavorite,
                    listCompanyFromPreviousPeriod = cacheRepository.getCompanyAfterYesterday()
                )
            } else {
                getDataFromNetwork(
                    dataGetReadyIsShow = dataGetReadyIsShow,
                    listFavorite = listFavorite
                )
            }
            dataGetReadyIsShow
        }
    }

    override suspend fun createListOneDayHalfYear(): Flow<List<Company>> {
        return cacheRepository.getFavoriteCompanies().map { favoriteCompanies ->
            val listFavorite = favoriteCompanies.map { it.secId }.toList()
            val dataGetReadyIsShow = mutableListOf<Company>()

            if (lastData == todayData) {
                getDataFromCash(
                    dataGetReadyIsShow = dataGetReadyIsShow,
                    listFavorite = listFavorite,
                    listCompanyFromPreviousPeriod = cacheRepository.getCompanyAfterYesterday()
                )
            } else {
                getDataFromNetwork(
                    dataGetReadyIsShow = dataGetReadyIsShow,
                    listFavorite = listFavorite
                )
            }
            dataGetReadyIsShow
        }
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

    override suspend fun createFavoriteCompany(): Flow<List<FavoriteCompanyShow>> {
        return cacheRepository.getFavoriteCompanies().map { favoriteCompanies ->
            val listFavorite = favoriteCompanies.map { it.secId }.toList()
            val dataGetReadyIsShow = mutableListOf<FavoriteCompanyShow>()
            val date = Date()
            listFavorite.forEach {
                val graph = networkRepository.getCompanyCandles(it, date.changeDay(-90), date)
                val changePerDay = networkRepository.getCompanyCandles(it, date.changeDay(-1), date)
                val changePerWeek =
                    networkRepository.getCompanyCandles(it, date.changeDay(-7), date)
                val changePerMonth =
                    networkRepository.getCompanyCandles(it, date.changeDay(-30), date)
                dataGetReadyIsShow.add(
                    FavoriteCompanyShow(
                        secId = it,
                        name = changePerDay.first().shortName,
                        listEntityCompany = graph,
                        changePricePerDay = FavoriteFactory(
                            changePerDay.first(), changePerDay.last()
                        ).changePriceFavorite(),
                        changePercentPerDay = FavoriteFactory(
                            changePerDay.first(), changePerDay.last()
                        ).changePercentFavorite(),
                        changePricePerWeek = FavoriteFactory(
                            changePerWeek.first(), changePerWeek.last()
                        ).changePriceFavorite(),
                        changePercentPerWeek = FavoriteFactory(
                            changePerWeek.first(), changePerWeek.last()
                        ).changePercentFavorite(),
                        changePricePerMonth = FavoriteFactory(
                            changePerMonth.first(), changePerMonth.last()
                        ).changePriceFavorite(),
                        changePercentPerMonth = FavoriteFactory(
                            changePerMonth.first(), changePerMonth.last()
                        ).changePercentFavorite(),
                        favorite = true
                    )
                )
            }
            dataGetReadyIsShow
        }
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

