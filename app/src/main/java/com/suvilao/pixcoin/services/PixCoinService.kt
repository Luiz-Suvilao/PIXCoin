package com.suvilao.pixcoin.services

import com.suvilao.pixcoin.interfaces.IPixCoinService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PixCoinService {
    private const val BASE_URL = "https://pixcoinapi.com/api/"

    val api: IPixCoinService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPixCoinService::class.java)
    }
}
