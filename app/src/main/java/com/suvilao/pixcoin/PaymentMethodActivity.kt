package com.suvilao.pixcoin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.suvilao.pixcoin.responses.Machine

class PaymentMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        val machine = intent.getSerializableExtra("machine") as Machine

        val btnPIX = findViewById<Button>(R.id.btnPIX)

        btnPIX.setOnClickListener {
            val intent = Intent(this, PIXActivity::class.java).apply {
                putExtra("machine", machine)
            }
            startActivity(intent)
        }
    }
}