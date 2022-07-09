package com.amk.core.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoexApiImpl {

    fun getMoexService(): MoexApiService {
        return createRetrofit().create(MoexApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://iss.moex.com/iss/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}