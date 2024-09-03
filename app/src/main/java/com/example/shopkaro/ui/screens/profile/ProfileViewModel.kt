package com.example.shopkaro.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shopkaro.data.repository.FirebaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ProfileUiState(val name: String = "", val email: String = "")

@HiltViewModel
class ProfileViewModel @Inject constructor(private val firebaseRepo: FirebaseRepo) : ViewModel() {

    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState>
        get() = _profileUiState

    init {
        fetchProfile()
    }

    private fun fetchProfile() {
        try {
            val user = firebaseRepo.user()
            if (user != null) {
                _profileUiState.update { profileState ->
                    profileState.copy(
                        name = user.email?.take(user?.email!!.indexOf("@")) ?: "Display Name", email = user.email ?: ""
                    )
                }
            }
        }catch (e:Exception){
            Log.d("MYDEBUG", e.message.toString())
        }

    }

    fun signOut() {
        firebaseRepo.signOut()
    }
}