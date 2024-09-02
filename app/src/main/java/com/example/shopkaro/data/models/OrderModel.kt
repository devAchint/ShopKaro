package com.example.shopkaro.data.models

data class OrderModel(
    val orderId: String,
    val items: List<CartItem>,
    val orderStatus: String,
    val date: String,
    val shippingDetails: ShippingDetailModel
) {
    constructor() : this("", emptyList(), "", "",  ShippingDetailModel())
}
