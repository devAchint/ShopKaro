package com.example.shopkaro.data.models

data class CartModel(
    val productId:Int,
    val productImage: String,
    val productName: String,
    val productPrice: Double,
    val productQuantity: Int
)

data class CartItem(val itemId: Int, val quantity: Int) {
    // No-argument constructor required for Firebase
    constructor() : this(0, 0)
}
