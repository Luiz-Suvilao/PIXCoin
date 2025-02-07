package com.suvilao.pixcoin.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.suvilao.pixcoin.repositories.PixCoinRepository
import com.suvilao.pixcoin.responses.GetLittleMachineResponse

class PixViewModel(private val pixRepository: PixCoinRepository) : ViewModel() {
    fun getLittleMachine(code: String, onResult: (GetLittleMachineResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = pixRepository.getLittleMachine(code)
                onResult(response)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }
}