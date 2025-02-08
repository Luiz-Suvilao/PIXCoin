package com.suvilao.pixcoin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.suvilao.pixcoin.services.PixCoinService
import com.suvilao.pixcoin.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    private val apiService = PixCoinService.api;

    private val mainViewModel = MainViewModel(apiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val code = editText.text.toString()
            if (code.isNotEmpty()) {
                getLittleMachine(code)
            } else {
                Toast.makeText(this, "Digite um código válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getLittleMachine(code: String) {
        mainViewModel.getLittleMachine(code) { response ->
            if (response != null) {
                Toast.makeText(this, "Máquina encontrada: ${response.maquina.corPrincipal}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Erro ao buscar as configurações da máquina!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
