package com.example.shopkaro.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shopkaro.Screens

@Composable
fun RegisterScreen(navigateToHome: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "home", modifier = Modifier.align(Alignment.Center).clickable {
            navigateToHome()
        })
    }
}