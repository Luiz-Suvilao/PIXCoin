package com.suvilao.pixcoin.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suvilao.pixcoin.interfaces.IPixCoinService
import kotlinx.coroutines.launch

import com.suvilao.pixcoin.responses.GetLittleMachineResponse

class PixViewModel(private val apiService: IPixCoinService) : ViewModel() {
    fun getLittleMachine(code: String, onResult: (GetLittleMachineResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getLittleMachine(code)
                onResult(response)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }
}