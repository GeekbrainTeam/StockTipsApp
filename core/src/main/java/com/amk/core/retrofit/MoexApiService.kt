package com.amk.core.retrofit

import retrofit2.http.GET

interface MoexApiService {
    @GET("history/engines/stock/markets/shares/boards/TQBR/securities.json")
    suspend fun getCompanyDataAsync(
        //@Query("date") date: String,
        //@Query("start") start: Int
    ): GsonCompanyResponseStructure
}