package com.example.shopkaro.ui.screens.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.models.CartModel
import com.example.shopkaro.data.repository.FirebaseRepo
import com.example.shopkaro.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CartUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val carts: List<CartModel> = emptyList()
)

@HiltViewModel
class CartViewModel @Inject constructor(
    private val firebaseRepo: FirebaseRepo,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState: StateFlow<CartUiState>
        get() = _cartUiState

    init {
        fetchCarts()
    }

    private fun fetchCarts() {
        viewModelScope.launch {
            try {
                 firebaseRepo.fetchCartItems().collect {
                    val carts = it.map {
                        val product = networkRepository.fetchProduct(it.itemId)
                        CartModel(
                            productId = it.itemId,
                            productQuantity = it.quantity,
                            productImage = product.image,
                            productPrice = product.price,
                            productName = product.title
                        )
                    }
                    _cartUiState.update {
                        it.copy(isLoading = false, carts = carts)
                    }
                }

            } catch (e: Exception) {
                _cartUiState.update {
                    it.copy(isLoading = false, error = e.message.toString())
                }
            }
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            try {
                firebaseRepo.addToCart(productId)
                val quantity = firebaseRepo.fetchCart(productId)
                _cartUiState.update { uiState ->
                    // Find the item you want to update
                    val updatedCarts = uiState.carts.map { cartItem ->
                        if (cartItem.productId == productId) {
                            // Return a copy of the item with the updated quantity
                            cartItem.copy(productQuantity = quantity)
                        } else {
                            // Return the item unchanged
                            cartItem
                        }
                    }
                    // Return a copy of the UI state with the updated carts list
                    uiState.copy(carts = updatedCarts)
                }
            } catch (e: Exception) {
                Log.d("MYDEBUG", e.message.toString())
            }
        }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            try {
                firebaseRepo.removeFromCart(productId)
                val quantity = firebaseRepo.fetchCart(productId)
                _cartUiState.update { uiState ->
                    // Find the item you want to update
                    val updatedCarts = uiState.carts.map { cartItem ->
                        if (cartItem.productId == productId) {
                            // Return a copy of the item with the updated quantity
                            cartItem.copy(productQuantity = quantity)
                        } else {
                            // Return the item unchanged
                            cartItem
                        }
                    }
                    // Return a copy of the UI state with the updated carts list
                    uiState.copy(carts = updatedCarts)
                }

            } catch (e: Exception) {
                Log.d("MYDEBUG", e.message.toString())
            }
        }
    }
}