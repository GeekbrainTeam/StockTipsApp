package com.amk.company.getinfo.retrofit

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface BankiRuApiService {
    @GET("investment/share/{isin}/")
    suspend fun getSite(
        @Path("isin") isin: String,
    ): ResponseBody
}