package com.amk.core.api

import com.amk.core.entity.company.MoexCompanyRaw
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoexApi {

    @GET("history/engines/stock/markets/shares/boards/TQBR/securities.json?iss.meta=on")
    suspend fun getCompaniesByPage(
        @Query("start") start: Int,
        @Query("date") date: String
    ): MoexCompanyRaw

    @GET("history/engines/stock/markets/shares/boards/TQBR/securities/{secId}/candles.json?start=0")
    suspend fun getMoexCandleByCompany(
        @Path("secId") secId: String,
        @Query("from") dataFrom: String,
        @Query("till") dataTill: String
    ): MoexCompanyRaw
}