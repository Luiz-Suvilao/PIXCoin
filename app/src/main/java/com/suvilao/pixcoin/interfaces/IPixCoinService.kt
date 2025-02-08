package com.suvilao.pixcoin.interfaces

import com.suvilao.pixcoin.responses.GetLittleMachineResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IPixCoinService {
    @GET("buscar-maquininha/{code}")
    suspend fun getLittleMachine(@Path("code") code: String): GetLittleMachineResponse
}