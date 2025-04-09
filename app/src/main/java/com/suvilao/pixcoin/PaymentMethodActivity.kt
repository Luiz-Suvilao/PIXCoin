package com.suvilao.pixcoin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.suvilao.pixcoin.helpers.MachineStorage
import com.suvilao.pixcoin.responses.Machine
import kotlinx.coroutines.launch

class PaymentMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        val machine = intent.getSerializableExtra("machine") as Machine

        addClickEventOnPixButton(machine)

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            lifecycleScope.launch {
                MachineStorage.deleteMachine(this@PaymentMethodActivity)

                val intent = Intent(this@PaymentMethodActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    private fun addClickEventOnPixButton(machine: Machine) {
        val btnPIX = findViewById<Button>(R.id.btnPIX)
        btnPIX.setOnClickListener {
            val intent = Intent(this, PIXActivity::class.java).apply {
                putExtra("machine", machine)
            }
            startActivity(intent)
        }
    }
}