package com.example.shopkaro.screens.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.models.ProductResponse
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
    val error: String? = null
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val savedStateHandle: SavedStateHandle
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
                _productDetailUiState.update { it.copy(product = product, isLoading = false) }
            } catch (e: Exception) {
                _productDetailUiState.update {
                    it.copy(error = e.message.toString(), isLoading = false)
                }
            }

        }
    }

    private fun addToCart(){

    }
}