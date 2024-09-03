package com.example.shopkaro.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopkaro.data.repository.FirebaseRepo
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false
)


@HiltViewModel
class RegisterViewModel @Inject constructor(private val firebaseRepo: FirebaseRepo) : ViewModel() {

    private val _registerUiState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState>
        get() = _registerUiState

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?>
        get() = _currentUser

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _registerUiState.update { currentState -> currentState.copy(isLoading = true) }
                firebaseRepo.register(email, password)
                _registerUiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        success = true
                    )
                }
            } catch (e: Exception) {
                _registerUiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        success = false,
                        errorMessage = e.message
                    )
                }
            }

        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _registerUiState.update { currentState -> currentState.copy(isLoading = true) }
                firebaseRepo.login(email, password)
                _registerUiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        success = true
                    )
                }
            } catch (e: Exception) {
                _registerUiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        success = false,
                        errorMessage = e.message
                    )
                }
            }

        }
    }

    fun resetRegisterState() {
        _registerUiState.update { RegisterUiState() }
    }
}