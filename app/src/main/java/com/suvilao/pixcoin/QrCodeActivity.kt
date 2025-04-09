package com.suvilao.pixcoin

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class QrCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        val qrCodeUrl = intent.getStringExtra("QR_CODE_URL")
        val imageViewQrCode = findViewById<ImageView>(R.id.imageViewQrCode)

        if (!qrCodeUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(qrCodeUrl)
                .into(imageViewQrCode)
        }
    }
}