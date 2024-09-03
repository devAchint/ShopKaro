package com.example.shopkaro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.shopkaro.data.models.ProductResponse
import com.example.shopkaro.ui.theme.Star


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier, navigateToProduct: (id: Int) -> Unit) {
    val viewmodel: HomeViewModel = hiltViewModel()
    val products = viewmodel.products.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Home") })
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 12.dp)

        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                item(span = { GridItemSpan(2) }) {
                    Image(
                        painter = rememberAsyncImagePainter("https://i.pinimg.com/564x/76/a2/ae/76a2ae7967725a93fb0cd42a1a28a8ba.jpg"),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                }
                items(products.value) {
                    Product(product = it, navigateToProduct)
                }
            }
        }

    }

}


@Composable
fun Product(product: ProductResponse, navigateToProduct: (id: Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .clickable { navigateToProduct(product.id) }
            .padding(16.dp)

    ) {
        Image(
            rememberAsyncImagePainter(product.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = product.title,
                maxLines = 1,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.weight(1f)
            )
            Row {
                Icon(
                    Icons.Filled.Star, contentDescription = null, tint = Star,
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${product.rating.rate}", color = Color.Black)
            }
        }

        Text(
            text = "₹" + product.price.toString(),
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}