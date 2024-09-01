package com.example.shopkaro.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.shopkaro.data.models.ProductResponse

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier, navigateToProduct: () -> Unit) {
    val viewmodel: HomeViewModel = hiltViewModel()
    val products = viewmodel.products.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "radio option 1") })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(Color.Red)
                .padding(innerPadding)
                .padding(bottom = 50.dp)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(products.value) {
                    Product(product = it)
                }
            }
        }

    }

}

@Composable
fun Product(product: ProductResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.image), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(text = product.title)
    }
}