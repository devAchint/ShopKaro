package com.example.shopkaro.ui.screens.payment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.repository.FirebaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PaymentUiState(
    val paymentMethod: String = "Cash on delivery",
    val paymentProcessing: Boolean = false,
    val paymentProcessed: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val firebaseRepo: FirebaseRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _paymentUiState = MutableStateFlow(PaymentUiState())
    val paymentUiState: StateFlow<PaymentUiState>
        get() = _paymentUiState

    private val orderId: String = checkNotNull(savedStateHandle["orderId"])

    fun placeOrder() {
        viewModelScope.launch {
            try {
                _paymentUiState.update { it.copy(paymentProcessing = true) }
                firebaseRepo.placeOrder(orderId)
                _paymentUiState.update {
                    it.copy(
                        paymentProcessing = false,
                        paymentProcessed = true
                    )
                }
            } catch (e: Exception) {
                _paymentUiState.update { it.copy(error = e.message, paymentProcessing = false) }
            }
        }
    }

    fun changePaymentMethod(paymentMethod: String) {
        _paymentUiState.update { it.copy(paymentMethod = paymentMethod) }
    }

    fun resetPaymentUiState() {
        _paymentUiState.update { PaymentUiState() }

    }
}