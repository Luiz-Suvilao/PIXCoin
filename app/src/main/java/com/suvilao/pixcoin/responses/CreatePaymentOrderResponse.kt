package com.suvilao.pixcoin.responses

import com.google.gson.annotations.SerializedName

data class QrCodeItem(
    @SerializedName("rel") val rel: String,
    @SerializedName("href") val href: String,
    @SerializedName("media") val media: String,
    @SerializedName("type") val type: String
)

data class CreatePaymentOrderResponse(
    @SerializedName("qr_codes") val qrCodes: List<QrCodeItem>,
    @SerializedName("client_token") val clientToken: String,
    @SerializedName("order_id") val orderId: String,
)