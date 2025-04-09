package com.suvilao.pixcoin.viewModels

import androidx.lifecycle.ViewModel
import com.suvilao.pixcoin.interfaces.IPixCoinService
import com.suvilao.pixcoin.responses.CreatePaymentOrderResponse

class PixViewModel(private val apiService: IPixCoinService) : ViewModel() {
    suspend fun createPaymentOrder(clientId: String, valor: String): CreatePaymentOrderResponse? {
        return try {
            apiService.createPaymentOrder(clientId, valor)
        } catch (e: Exception) {
            null
        }
    }
}