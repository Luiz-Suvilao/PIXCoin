package com.suvilao.pixcoin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.suvilao.pixcoin.services.PagBankServiceSandBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QrCodeActivity : AppCompatActivity() {
    private val pagBankService: PagBankServiceSandBox = PagBankServiceSandBox;
        private var orderCheckJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        val qrCodeUrl = intent.getStringExtra("QR_CODE_URL")
        val clientToken = intent.getStringExtra("clientToken")
        val orderId = intent.getStringExtra("orderId")

        val imageViewQrCode = findViewById<ImageView>(R.id.imageViewQrCode)

        if (!qrCodeUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(qrCodeUrl)
                .into(imageViewQrCode)

            val btnBack = findViewById<Button>(R.id.btnBack)
            btnBack.setOnClickListener {
                finish()
            }
        }

        startOrderStatusCheck(orderId, clientToken)
    }

    private fun startOrderStatusCheck(orderId: String?, token: String?) {
        if (orderId == null || token == null) return

        orderCheckJob = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        pagBankService.api.getOrder(orderId, "Bearer $token")
                    }

                    val isPaid = response.charges?.any { charge ->
                        charge.status == "PAID"
                    } ?: false

                    if (isPaid) {
                        showPaymentSuccess()
                        cancel()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun showPaymentSuccess() {
        Toast.makeText(this, "Pagamento confirmado!", Toast.LENGTH_SHORT).show()
         startActivity(Intent(this, PaymentSuccessActivity::class.java))
         finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        orderCheckJob?.cancel()
    }
}