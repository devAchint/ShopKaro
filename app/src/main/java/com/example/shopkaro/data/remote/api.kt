package com.example.shopkaro.data.remote

import com.example.shopkaro.data.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {
    @GET("products")
    suspend fun fetchProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun fetchProduct(@Path("id") id: Int): ProductResponse
}