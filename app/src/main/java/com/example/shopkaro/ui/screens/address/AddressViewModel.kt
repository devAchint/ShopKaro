package com.example.shopkaro.ui.screens.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.models.ShippingDetailModel
import com.example.shopkaro.data.repository.FirebaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddressUiState(
    val orderId: String? = null,
    val addressProcessing: Boolean = false,
    val addressProcessed: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AddressViewModel @Inject constructor(private val firebaseRepo: FirebaseRepo) : ViewModel() {

    private val _addressUiState = MutableStateFlow<AddressUiState>(AddressUiState())
    val addressUiState: StateFlow<AddressUiState>
        get() = _addressUiState

    fun addOrder(shippingDetails: ShippingDetailModel) {
        viewModelScope.launch {
            try {
                _addressUiState.value = AddressUiState(addressProcessing = true)
                val orderId = firebaseRepo.addOrder(shippingDetails)
                _addressUiState.value =
                    AddressUiState(
                        addressProcessing = false,
                        addressProcessed = true,
                        orderId = orderId
                    )
            } catch (e: Exception) {
                _addressUiState.value = AddressUiState(addressProcessing = false, error = e.message)
            }
        }
    }

    fun resetAddressUiState(){
        _addressUiState.value = AddressUiState()
    }
}