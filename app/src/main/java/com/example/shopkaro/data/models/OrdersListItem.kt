package com.example.shopkaro.data.models

data class OrdersListItem(
    val id: String,
    val status: String,
    val date: String,
    val productImage: String,
    val productName: String,
    val productDescription: String
)