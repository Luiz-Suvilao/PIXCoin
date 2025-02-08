package com.suvilao.pixcoin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.suvilao.pixcoin.helpers.MachineStorage
import com.suvilao.pixcoin.responses.Machine
import com.suvilao.pixcoin.services.PixCoinService
import com.suvilao.pixcoin.viewModels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val apiService = PixCoinService.api

    private val mainViewModel = MainViewModel(apiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMachineAndVerifyRedirect()

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
        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) { mainViewModel.getLittleMachineSync(code) }
            if (response != null) {
                withContext(Dispatchers.IO) {
                    MachineStorage.saveMachine(this@MainActivity, response.maquina)
                    navigateToPaymentView(response.maquina)
                }
            } else {
                Toast.makeText(this@MainActivity, "Erro ao buscar as configurações da máquina!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun loadSavedMachine(): Machine? {
        return MachineStorage.getMachine(this@MainActivity)
    }

    private fun loadMachineAndVerifyRedirect() {
        lifecycleScope.launch {
            val machine = loadSavedMachine()
            machine?.let {
                Toast.makeText(this@MainActivity, "Máquina carregada: ${it.codigo}", Toast.LENGTH_SHORT).show()
                navigateToPaymentView(it)
            }
        }
    }

    private fun navigateToPaymentView(machine: Machine) {
        val intent = Intent(this@MainActivity, PaymentMethodActivity::class.java).apply {
            putExtra("machine", machine)
        }
        startActivity(intent)
        finish()
    }
}
