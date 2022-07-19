package com.amk.core.interactors

import com.amk.core.entity.EntityCompany
import com.amk.core.entity.FavoriteCompany
import com.amk.core.repository.NetworkRepository
import com.amk.core.utils.changeDay
import java.util.*

class FavoriteFactory(
    private val favoriteCompany: FavoriteCompany,
    private val networkRepository: NetworkRepository
) {
    val date = Date()
    suspend fun getCompanyCandles(secId: String): List<EntityCompany> {
        return networkRepository.getCompanyCandles(secId, date.changeDay(-90), date)
    }

    suspend fun changePricePerDay (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-1), date)
        return tmp.last().close - tmp.first().close
    }

    suspend fun changePercentPerDay (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-1), date)
        return (tmp.last().close - tmp.first().close)*100/tmp.first().close
    }
    suspend fun changePricePerWeek (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-7), date)
        return tmp.last().close - tmp.first().close
    }
    suspend fun changePercentPerWeek (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-7), date)
        return (tmp.last().close - tmp.first().close)*100/tmp.first().close
    }

    suspend fun changePricePerMonth (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-30), date)
        return tmp.last().close - tmp.first().close
    }

    suspend fun changePercentPerMonth (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-30), date)
        return (tmp.last().close - tmp.first().close)*100/tmp.first().close
    }

    suspend fun changePricePerQuarter (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-90), date)
        return tmp.last().close - tmp.first().close
    }
    suspend fun changePercentPerQuarter (secId: String) : Double {
        val tmp = networkRepository.getCompanyCandles(secId, date.changeDay(-90), date)
        return (tmp.last().close - tmp.first().close)*100/tmp.first().close
    }
}