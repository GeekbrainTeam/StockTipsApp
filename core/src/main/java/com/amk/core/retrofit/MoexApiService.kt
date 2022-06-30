package com.amk.core.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface MoexApiService {
    @GET("history/engines/stock/markets/shares/boards/TQBR/securities.json")
    suspend fun getCompaniesPage(
        //@Query("date") date: String,
        @Query("start") start: Long = 0
    ): GsonCompaniesPageResponseStructure
}