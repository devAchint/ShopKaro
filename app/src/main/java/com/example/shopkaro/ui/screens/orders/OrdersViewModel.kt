package com.example.shopkaro.ui.screens.orders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.models.OrdersListItem
import com.example.shopkaro.data.repository.FirebaseRepo
import com.example.shopkaro.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrderUiState(
    val isLoading: Boolean = false,
    val orders: List<OrdersListItem> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val firebaseRepo: FirebaseRepo,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _orderUiState = MutableStateFlow<OrderUiState>(OrderUiState())
    val orderUiState: StateFlow<OrderUiState>
        get() = _orderUiState

    init {
        fetchOrders()
    }

    private fun fetchOrders() {
        viewModelScope.launch {
            try {
                _orderUiState.update { it.copy(isLoading = true) }
                val orders = firebaseRepo.fetchOrders()
                val orderItems = orders.map {
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
                _orderUiState.update { it.copy(isLoading = false, orders = orderItems) }
            } catch (e: Exception) {
                _orderUiState.update { it.copy(isLoading = false, error = e.message.toString()) }
                Log.d("MYDEBUG", e.message.toString())
            }
        }
    }
}