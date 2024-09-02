package com.example.shopkaro.screens.orders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.repository.FirebaseRepo
import com.example.shopkaro.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrdersListItem(
    val id: String,
    val status: String,
    val date: String,
    val productImage: String,
    val productName: String,
    val productDescription: String
)

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val firebaseRepo: FirebaseRepo,
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private val _orders = MutableStateFlow<List<OrdersListItem>>(emptyList())
    val orders: StateFlow<List<OrdersListItem>>
        get() = _orders

    init {
        fetchOrders()
    }

    private fun fetchOrders() {
        viewModelScope.launch {
            try {
                val orders = firebaseRepo.fetchOrders()
                _orders.value = orders.map {
                    val product = networkRepository.fetchProduct(it.items[0].itemId)
                    OrdersListItem(
                        id = it.orderId,
                        status = it.orderStatus,
                        date = it.date,
                        productImage = product.image,
                        productName = product.title,
                        productDescription = product.description
                    )
                }
            } catch (e: Exception) {
                Log.d("MYDEBUG", e.message.toString())
            }
        }
    }
}