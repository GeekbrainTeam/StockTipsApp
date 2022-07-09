package com.amk.core.interactor

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.amk.core.entity.CacheModel
import com.amk.core.entity.CompanyIsShow
import com.amk.core.repository.RepositoryCacheDb
import com.amk.core.repository.RepositoryInternet
import com.amk.core.utils.FARMAT_DATA
import com.amk.core.utils.STORAGE_NAME
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CacheInteractorImpl(
    private val context: Context,
    private val repositoryCacheDb: RepositoryCacheDb,
    repository: RepositoryInternet
) : CacheInterctor {
    private val dataGetReadyIsShow = mutableListOf<CompanyIsShow>()
    override val dataFromCache = repositoryCacheDb.selectAllItems()
    override val dataFromInternetToday = repository.getCompanies()
    override val dataFromInternetLast = repository.getCompaniesLastData()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun compareCacheVsInternet(): List<CompanyIsShow> {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(STORAGE_NAME, MODE_PRIVATE)
        val lastData = sharedPreferences.getString(STORAGE_NAME, "")
        val todayData = currentDataToString()
        if (lastData == todayData) {
            dataFromCache.value?.forEach {
                dataGetReadyIsShow.add(
                    CompanyIsShow(
                        shortName = it.shortName,
                        changePrice = it.changePrice,
                        changePercent = it.changePercent,
                        favorite = it.isFavorite
                    )
                )
            }
        } else {
            dataFromInternetToday.forEach { today ->
                dataFromInternetLast.forEach { last ->
                    if (today.shortName == last.shortName) {
                        val changePrice = createChangePrice(last.close, today.close)
                        val changePercent = createChangePercent(last.close, today.close)
                        val choseCacheDb = repositoryCacheDb.selectItem(today.shortName)
                        if (choseCacheDb != null) {
                            repositoryCacheDb.updateCacheItem(
                                CacheModel(
                                    isFavorite = choseCacheDb.isFavorite,
                                    tradeDate = today.tradeDate,
                                    shortName = today.shortName,
                                    secId = today.secId,
                                    open = today.open,
                                    low = today.low,
                                    high = today.high,
                                    close = today.close,
                                    changePrice = changePrice,
                                    changePercent = changePercent
                                )
                            )
                            dataGetReadyIsShow.add(
                                CompanyIsShow(
                                    shortName = today.shortName,
                                    changePrice = changePrice,
                                    changePercent = changePercent,
                                    favorite = choseCacheDb.isFavorite
                                )
                            )
                        } else {
                            repositoryCacheDb.addCacheItem(
                                CacheModel(
                                    isFavorite = false,
                                    tradeDate = today.tradeDate,
                                    shortName = today.shortName,
                                    secId = today.secId,
                                    open = today.open,
                                    low = today.low,
                                    high = today.high,
                                    close = today.close,
                                    changePrice = changePrice,
                                    changePercent = changePercent
                                )
                            )
                            dataGetReadyIsShow.add(
                                CompanyIsShow(
                                    shortName = today.shortName,
                                    changePrice = changePrice,
                                    changePercent = changePercent,
                                    favorite = false
                                )
                            )
                        }
                    }
                }
            }
            sharedPreferences.edit().putString(STORAGE_NAME, todayData).apply()
        }
        return dataGetReadyIsShow
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDataToString(): String {
        val currentData = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(FARMAT_DATA)
        return currentData.format(formatter).toString()
    }

    private fun createChangePrice(lastPrice: Double, currentPrice: Double) =
        currentPrice - lastPrice

    private fun createChangePercent(lastPrice: Double, currentPrice: Double) =
        (currentPrice - lastPrice) * 100 / (lastPrice)
}

