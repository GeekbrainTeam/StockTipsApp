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
import kotlinx.coroutines.flow.first
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
    private var currentFavorite = -1

    override suspend fun createListOneDayYesterday(): Flow<Set<Company>> {
        return cacheRepository.getFavoriteCompanies().map { favoriteCompanies ->
            val dataGetReadyIsShow = mutableSetOf<Company>()
            val listFavorite = favoriteCompanies.map { it.secId }.toList()
            if (currentFavorite != -1 && currentFavorite == favoriteCompanies.size) {
                updateFavorite(favoriteCompanies, listFavorite, dataGetReadyIsShow)
                dataGetReadyIsShow
            } else {
                updateListCompanies(dataGetReadyIsShow, listFavorite, favoriteCompanies)
            }
        }
    }

    private suspend fun updateListCompanies(
        dataGetReadyIsShow: MutableSet<Company>,
        listFavorite: List<String>,
        favoriteCompanies: List<EntityFavoriteCompany>
    ): MutableSet<Company> {
        if (lastData == todayData) {
            getDataFromCash(
                dataGetReadyIsShow = dataGetReadyIsShow,
                listFavorite = listFavorite,
                listCompanyFromPreviousPeriod = cacheRepository.getCompanyAfterYesterday()
            )
        } else {
            fillDataFromNetwork(
                dataGetReadyIsShow = dataGetReadyIsShow,
                listFavorite = listFavorite
            )
        }
        currentFavorite = favoriteCompanies.size
        return dataGetReadyIsShow
    }

    private suspend fun updateFavorite(
        favoriteCompanies: List<EntityFavoriteCompany>,
        listFavorite: List<String>,
        dataGetReadyIsShow: MutableSet<Company>
    ) {
        val listCompanyNetworkOneDay = cacheRepository.getCompanyOneDay()
        val listCompanyNetworkAfterYesterday = cacheRepository.getCompanyAfterYesterday()
        val listCompanyNetworkNetworkHalfYear = cacheRepository.getCompanyHalfYear()
        favoriteCompanies.map { favorite ->
            CompanyFactory(
                listOf(listCompanyNetworkOneDay.first { it.secId == favorite.secId }
                    .convertToEntityCompany()),
                listOf(listCompanyNetworkAfterYesterday.first { it.secId == favorite.secId }
                    .convertToEntityCompany()),
                listFavorite
            ).getCompanies().first()
        }.forEach {
            dataGetReadyIsShow.add(it)
        }
    }

    override suspend fun createListOneDayHalfYear(): Flow<Set<Company>> {
        return cacheRepository.getFavoriteCompanies().map { favoriteCompanies ->
            val listFavorite = favoriteCompanies.map { it.secId }.toList()
            val dataGetReadyIsShow = mutableSetOf<Company>()

            if (lastData == todayData) {
                getDataFromCash(
                    dataGetReadyIsShow = dataGetReadyIsShow,
                    listFavorite = listFavorite,
                    listCompanyFromPreviousPeriod = cacheRepository.getCompanyAfterYesterday()
                )
            } else {
                fillDataFromNetwork(
                    dataGetReadyIsShow = dataGetReadyIsShow,
                    listFavorite = listFavorite
                )
            }
            dataGetReadyIsShow
        }
    }

    private suspend fun fillDataFromNetwork(
        dataGetReadyIsShow: MutableSet<Company>,
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
        dataGetReadyIsShow: MutableSet<Company>,
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
        cacheRepository.addFavoriteCompany(EntityFavoriteCompany(secId))
    }

    override suspend fun deleteFavoriteCompany(secId: String) {
        cacheRepository.deleteFavoriteCompany(secId)
    }

    override suspend fun createFavoriteCompany(): Flow<List<FavoriteCompany>> {
        return cacheRepository.getFavoriteCompanies().map { favoriteCompanies ->
            val listFavorite = favoriteCompanies.map { it.secId }.toList()
            val dataGetReadyIsShow = mutableListOf<FavoriteCompany>()
            val date = Date()
            listFavorite.forEach {
                val graph = networkRepository.getCompanyCandles(it, date.changeDay(-90), date)
                dataGetReadyIsShow.add(
                    FavoriteFactory(graph).getFavoriteCompany()
                )
            }
            dataGetReadyIsShow
        }
    }

    override suspend fun getAllFavorite(): List<EntityFavoriteCompany> =
        cacheRepository.getFavoriteCompanies().first()

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

