package com.example.shopkaro.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopkaro.data.models.ProductResponse

@Composable
fun HomeScreen(modifier: Modifier, navigateToProduct: () -> Unit) {
    val viewmodel: HomeViewModel = hiltViewModel()
    val products = viewmodel.products.collectAsState()
    LazyColumn {
        items(products.value) {
            Product(product = it)
        }
    }
}

@Composable
fun Product(product: ProductResponse) {
    Column {
        Text(text = product.title)
    }
}