package com.example.shopkaro.ui.screens.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.shopkaro.data.models.OrdersListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(ordersUiState: OrderUiState, navigateToOrderDetail: (orderId: String) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "My Orders") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (ordersUiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                if (ordersUiState.orders.isEmpty()) {
                    Text(text = "No orders found", modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyColumn {
                        items(ordersUiState.orders) {
                            OrderItem(it) {
                                navigateToOrderDetail(it.id)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
            ordersUiState.error?.let {
                Text(text = it, modifier = Modifier.align(Alignment.Center))
            }
        }

    }

}

@Composable
fun OrderItem(order: OrdersListItem, navigateToOrderDetail: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .clickable { navigateToOrderDetail() }
            .padding(12.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(order.productImage), contentDescription = null,
            modifier = Modifier
                .width(48.dp)
                .height(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = order.productName, color = Color.Black, maxLines = 1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = order.productDescription,
                maxLines = 2,
                color = Color.Black.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (order.status == "Success")
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(0.dp))
                        .border(width = 1.dp, shape = RoundedCornerShape(0.dp), color = Color.Black)
                        .padding(6.dp),
                    text = "Rate Order", color = Color.Black
                )
            else
                Text(text = "Order Status: ${order.status}", color = Color.Black.copy(alpha = 0.6f))
        }
    }
}