package com.example.shopkaro.data.models

data class OrderModel(
    val orderId: String,
    val items: List<CartModel>,
    val orderStatus: String,
    val date: String,
    val shippingDetails: ShippingDetailModel
)
