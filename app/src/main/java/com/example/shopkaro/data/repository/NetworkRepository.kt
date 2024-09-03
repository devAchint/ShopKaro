package com.example.shopkaro.data.repository

import com.example.shopkaro.data.remote.NetworkApi
import com.example.shopkaro.data.models.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val networkApi: NetworkApi) {

    suspend fun fetchProducts(): List<ProductResponse> {
        return withContext(Dispatchers.IO) {
            networkApi.fetchProducts()
        }
    }

    suspend fun fetchProduct(id: Int): ProductResponse {
        return withContext(Dispatchers.IO) {
            networkApi.fetchProduct(id)
        }
    }
}