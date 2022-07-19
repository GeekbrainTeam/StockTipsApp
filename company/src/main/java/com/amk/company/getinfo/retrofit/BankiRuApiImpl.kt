package com.amk.company.getinfo.retrofit

import retrofit2.Retrofit

class BankiRuApiImpl {

    fun getBankiRuApiService(): BankiRuApiService {
        return createRetrofit().create(BankiRuApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.banki.ru/")
            .build()
    }
}