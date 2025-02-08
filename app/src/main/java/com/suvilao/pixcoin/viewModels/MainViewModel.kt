package com.suvilao.pixcoin.viewModels

import androidx.lifecycle.ViewModel
import com.suvilao.pixcoin.interfaces.IPixCoinService

import com.suvilao.pixcoin.responses.GetLittleMachineResponse

class MainViewModel(private val apiService: IPixCoinService) : ViewModel() {
    suspend fun getLittleMachineSync(code: String): GetLittleMachineResponse? {
        return try {
            apiService.getLittleMachine(code)
        } catch (e: Exception) {
            null
        }
    }
}