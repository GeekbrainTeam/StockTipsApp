package com.amk.core.retrofit

import com.amk.core.entity.toStringU
import com.amk.core.entity.yesterdayU
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MoexApiService {
    @GET("history/engines/stock/markets/shares/boards/TQBR/securities.json")
    suspend fun getCompaniesPage(
        //@Query("date") date: String = Date().yesterdayU().toStringU(),
        @Query("start") start: Long = 0
    ): GsonCompaniesPageResponseStructure

    @GET("history/engines/stock/markets/shares/boards/TQBR/securities/{secId}/candles.json?start=0")
    suspend fun getCompanyCandlesPage(
        @Path("secId") secId: String,
        @Query("from") dataFrom: String = "1970-01-01",
        @Query("till") dataTill: String = Date().toStringU(),
        @Query("start") start: Long = 0
    ): GsonCompaniesPageResponseStructure
}