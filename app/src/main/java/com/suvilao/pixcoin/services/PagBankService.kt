package com.suvilao.pixcoin.services

import com.suvilao.pixcoin.interfaces.IPagBankService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PagBankService {
    private const val BASE_URL = "https://api.pagseguro.com/"

    val api: IPagBankService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPagBankService::class.java)
    }
}