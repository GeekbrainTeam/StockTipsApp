package com.amk.core.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://iss.moex.com/iss/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val moexService = retrofit.create(MoexApi::class.java)

}