package com.suvilao.pixcoin.interfaces

import com.suvilao.pixcoin.responses.OrderResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IPagBankService {
    @GET("orders/{orderId}")
    suspend fun getOrder(
        @Path("orderId") orderId: String,
        @Header("Authorization") token: String
    ): OrderResponse
}