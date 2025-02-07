package com.suvilao.pixcoin.repositories

import com.suvilao.pixcoin.interfaces.IPixCoinService
import com.suvilao.pixcoin.responses.GetLittleMachineResponse

class PixCoinRepository(private val pixCoinService: IPixCoinService) {
    fun getLittleMachine(code: String): GetLittleMachineResponse {
        return pixCoinService.getLittleMachine(code)
    }
}