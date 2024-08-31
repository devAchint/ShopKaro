package com.example.shopkaro.data.repository

import com.example.shopkaro.NetworkApi
import com.example.shopkaro.data.models.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val networkApi: NetworkApi) {

    suspend fun fetchProducts(): List<ProductResponse> {
        return withContext(Dispatchers.IO) {
            networkApi.fetchProducts()
        }
    }
}