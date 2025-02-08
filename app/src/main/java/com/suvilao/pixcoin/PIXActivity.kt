package com.suvilao.pixcoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suvilao.pixcoin.services.PixCoinService
import com.suvilao.pixcoin.viewModels.PixViewModel

class PIXActivity : AppCompatActivity() {
    private val apiService = PixCoinService.api;

    private val pixViewModel = PixViewModel(apiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pix)
    }
}
