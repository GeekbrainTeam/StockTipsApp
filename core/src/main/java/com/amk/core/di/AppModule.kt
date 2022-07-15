package com.amk.core.di

import com.amk.core.retrofit.MoexApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://iss.moex.com/iss/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApiService(retrofit: Retrofit): MoexApiService =
    retrofit.create(MoexApiService::class.java)
