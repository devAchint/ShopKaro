package com.example.shopkaro.ui.screens.product_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.models.ProductResponse
import com.example.shopkaro.data.repository.FirebaseRepo
import com.example.shopkaro.data.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductDetailState(
    val isLoading: Boolean = true,
    val product: ProductResponse? = null,
    val error: String? = null,
    val cartQuantity: Int = 0
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val savedStateHandle: SavedStateHandle,
    private val firebaseRepo: FirebaseRepo
) :
    ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])
    private val _productDetailUiState = MutableStateFlow<ProductDetailState>(ProductDetailState())
    val productDetailUiState: StateFlow<ProductDetailState>
        get() = _productDetailUiState

    init {
        fetchProduct(productId)
    }

    private fun fetchProduct(productId: Int) {
        viewModelScope.launch {
            try {
                val product = networkRepository.fetchProduct(productId)
                val quantity = firebaseRepo.fetchCart(productId)
                _productDetailUiState.update {
                    it.copy(
                        product = product,
                        isLoading = false,
                        cartQuantity = quantity
                    )
                }
            } catch (e: Exception) {
                _productDetailUiState.update {
                    it.copy(error = e.message.toString(), isLoading = false)
                }
            }

        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            try {
                firebaseRepo.addToCart(productId)
                val quantity = firebaseRepo.fetchCart(productId)
                _productDetailUiState.update {
                    it.copy(cartQuantity = quantity)
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
                _productDetailUiState.update {
                    it.copy(cartQuantity = quantity)
                }
            } catch (e: Exception) {
                Log.d("MYDEBUG", e.message.toString())
            }
        }
    }

}