package com.example.shopkaro

import com.example.shopkaro.data.models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("products")
    suspend fun fetchProducts(): List<ProductResponse>
}