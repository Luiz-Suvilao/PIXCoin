package com.suvilao.pixcoin.interfaces

import com.suvilao.pixcoin.responses.CreatePaymentOrderResponse
import com.suvilao.pixcoin.responses.GetLittleMachineResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IPixCoinService {
    @GET("buscar-maquininha/{code}")
    suspend fun getLittleMachine(@Path("code") code: String): GetLittleMachineResponse

    @POST("create-payment-order/{clientId}")
    suspend fun createPaymentOrder(
        @Path("clientId") clientId: String,
        @Query("valor") valor: String
    ): CreatePaymentOrderResponse
}