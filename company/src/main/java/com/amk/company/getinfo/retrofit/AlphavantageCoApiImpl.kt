package com.amk.company.getinfo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlphavantageCoApiImpl {

    fun getAlphavantageCoService(): AlphavantageCoApiService {
        return createRetrofit().create(AlphavantageCoApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://alphavantage.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}