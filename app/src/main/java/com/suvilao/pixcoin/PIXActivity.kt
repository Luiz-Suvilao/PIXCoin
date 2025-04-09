package com.suvilao.pixcoin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.suvilao.pixcoin.responses.Machine
import com.suvilao.pixcoin.services.PixCoinService
import com.suvilao.pixcoin.viewModels.PixViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

class PIXActivity : AppCompatActivity() {
    private val apiService = PixCoinService.api

    private val pixViewModel = PixViewModel(apiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pix)

        val machine = intent.getSerializableExtra("machine") as Machine
        renderValuesButtons(machine)
    }

    private fun renderValuesButtons(machine: Machine) {
        val buttonContainer = findViewById<LinearLayout>(R.id.buttonContainer)
        val modelo = findViewById<Button>(R.id.btnModelo)

        val valores = listOf(machine.valor1, machine.valor2, machine.valor3, machine.valor4)

        for (valor in valores) {
            if (valor > 0) {
                val formattedValue = formatCurrency(valor.toDouble())
                val button = Button(this).apply {
                    text = formattedValue
                    layoutParams = modelo.layoutParams
                    background = modelo.background
                    setTextColor(modelo.currentTextColor)
                    textSize = modelo.textSize

                    setOnClickListener {
                        Toast.makeText(this@PIXActivity, "Selecionado: $formattedValue", Toast.LENGTH_SHORT).show()
                        createPaymentOrder(machine.idCliente, valor.toString())
                    }
                }

                buttonContainer.addView(button)
            }
        }

    }

    private fun formatCurrency(value: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return format.format(value)
    }

    private fun createPaymentOrder(clientId: String, valor: String) {
        lifecycleScope.launch {
            try {
                val progressDialog = ProgressDialog(this@PIXActivity).apply {
                    setMessage("Gerando QR Code...")
                    setCancelable(false)
                    show()
                }

                val response = pixViewModel.createPaymentOrder(clientId, valor)

                progressDialog.dismiss()

                response?.qrCodes?.firstOrNull()?.let { qrCode ->
                    val intent = Intent(this@PIXActivity, QrCodeActivity::class.java).apply {
                        putExtra("QR_CODE_URL", qrCode.href)
                        putExtra("QR_CODE_TYPE", qrCode.rel)
                    }
                    startActivity(intent)

                } ?: run {
                    Toast.makeText(
                        this@PIXActivity,
                        "Não foi possível gerar o QR Code!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@PIXActivity,
                    "Erro: ${e.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

