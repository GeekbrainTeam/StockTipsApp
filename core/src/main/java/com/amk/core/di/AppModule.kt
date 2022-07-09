package com.amk.core.di

import com.amk.core.retrofit.MoexApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }
}
//private fun provideNetworkHelper(context: Context) = NetworkHelper(context)
//
//private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
//    val loggingInterceptor = HttpLoggingInterceptor()
//    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//    OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
//        .build()
//} else OkHttpClient
//    .Builder()
//    .build()

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://iss.moex.com/iss/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApiService(retrofit: Retrofit): MoexApiService =
    retrofit.create(MoexApiService::class.java)

//private fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper