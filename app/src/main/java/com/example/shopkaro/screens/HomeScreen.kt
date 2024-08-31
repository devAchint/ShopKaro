package com.example.shopkaro.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier, navigateToProduct: () -> Unit) {
    Text(text = "home", modifier = modifier.padding(80.dp).clickable { navigateToProduct() })
}