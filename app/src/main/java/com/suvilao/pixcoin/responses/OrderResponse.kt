package com.suvilao.pixcoin.responses

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("id") val id: String,
    @SerializedName("reference_id") val referenceId: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("charges") val charges: List<Charge>? = null
)

data class Charge(
    @SerializedName("id") val id: String,
    @SerializedName("reference_id") val referenceId: String?,
    @SerializedName("status") val status: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("paid_at") val paidAt: String?,
    @SerializedName("amount") val amount: Amount,
    @SerializedName("payment_response") val paymentResponse: PaymentResponse,
    @SerializedName("payment_method") val paymentMethod: PaymentMethod,
    @SerializedName("links") val links: List<Link>?,
    @SerializedName("metadata") val metadata: Map<String, Any>? = null
)

data class Amount(
    @SerializedName("value") val value: Int,
    @SerializedName("currency") val currency: String,
    @SerializedName("summary") val summary: Summary
)

data class Summary(
    @SerializedName("total") val total: Int,
    @SerializedName("paid") val paid: Int,
    @SerializedName("refunded") val refunded: Int
)

data class PaymentResponse(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String
)

data class PaymentMethod(
    @SerializedName("type") val type: String,
    @SerializedName("pix") val pix: Pix?
)

data class Pix(
    @SerializedName("notification_id") val notificationId: String?,
    @SerializedName("end_to_end_id") val endToEndId: String?,
    @SerializedName("holder") val holder: Holder
)

data class Holder(
    @SerializedName("name") val name: String,
    @SerializedName("tax_id") val taxId: String
)

data class Link(
    @SerializedName("rel") val rel: String,
    @SerializedName("href") val href: String,
    @SerializedName("media") val media: String?,
    @SerializedName("type") val type: String?
)