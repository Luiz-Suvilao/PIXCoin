package com.suvilao.pixcoin

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.suvilao.pixcoin.responses.Machine
import java.text.NumberFormat
import java.util.Locale

class PIXActivity : AppCompatActivity() {
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
}

