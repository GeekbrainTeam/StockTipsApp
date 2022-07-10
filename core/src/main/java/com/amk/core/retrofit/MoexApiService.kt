package com.amk.core.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoexApiService {
    @GET("history/engines/stock/markets/shares/boards/TQBR/securities.json")
    suspend fun getCompaniesLastDatePage(
        @Query("start") start: Long = 0
    ): GsonCompaniesPageResponseStructure

    @GET("history/engines/stock/markets/shares/boards/TQBR/securities.json")
    suspend fun getCompaniesByDatePage(
        @Query("date") date: String,
        @Query("start") start: Long = 0
    ): GsonCompaniesPageResponseStructure

    @GET("history/engines/stock/markets/shares/boards/TQBR/securities/{secId}/candles.json?start=0")
    suspend fun getCompanyCandlesPage(
        @Path("secId") secId: String,
        @Query("from") dateFrom: String,
        @Query("till") dateTill: String,
        @Query("start") start: Long = 0
    ): GsonCompaniesPageResponseStructure
}