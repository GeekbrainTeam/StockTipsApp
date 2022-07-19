package com.amk.company.getinfo.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import com.amk.company.getinfo.retrofit.CompanyInfoDto

interface AlphavantageCoApiService {
    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") secId: String,
        @Query("apikey") apiKey: String = "G1USXWKX272RK4BP"
    ): CompanyInfoDto
}