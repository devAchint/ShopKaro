package com.example.shopkaro.ui.screens.order_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.models.CartModel
import com.example.shopkaro.data.models.OrderModel
import com.example.shopkaro.data.repository.FirebaseRepo
import com.example.shopkaro.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrderDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val orderModel: OrderModel? = null
)

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val firebaseRepo: FirebaseRepo,
    private val networkRepository: NetworkRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _orderDetailUiState = MutableStateFlow(OrderDetailState())
    val orderDetailUiState: StateFlow<OrderDetailState>
        get() = _orderDetailUiState

    private val orderId: String = checkNotNull(savedStateHandle["orderId"])

    init {
        fetchOrder()
    }

    private fun fetchOrder() {
        viewModelScope.launch {
            try {
                _orderDetailUiState.update { it.copy(isLoading = true) }
                val order = firebaseRepo.fetchOrder(orderId)
                order?.let {
                    val items = order.items.map {
                        val product = networkRepository.fetchProduct(it.itemId)
                        CartModel(
                            productId = it.itemId,
                            productName = product.title,
                            productPrice = product.price,
                            productImage = product.image,
                            productQuantity = it.quantity
                        )
                    }
                    val orderModel = OrderModel(
                        orderId = order.orderId,
                        items = items,
                        orderStatus = order.orderStatus,
                        date = order.date,
                        shippingDetails = order.shippingDetails
                    )
                    _orderDetailUiState.update {
                        it.copy(
                            isLoading = false,
                            orderModel = orderModel
                        )
                    }
                } ?: kotlin.run {
                    _orderDetailUiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Order not found"
                        )
                    }
                }

            } catch (e: Exception) {
                _orderDetailUiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }
}