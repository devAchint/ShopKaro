package com.example.shopkaro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopkaro.ui.theme.BoxColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(navigateToOrderDetail: () -> Unit) {
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OrderItem(navigateToOrderDetail)
        }
    }
}

@Composable
fun OrderItem(navigateToOrderDetail: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BoxColor)
            .padding(12.dp).clickable { navigateToOrderDetail() }
    ) {
        Image(
            Icons.Filled.Face, contentDescription = null,
            modifier = Modifier
                .width(48.dp)
                .height(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Delivered on ", color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Item descriptionItem descriptionItem descriptionItem description",
                maxLines = 2,
                color = Color.Black.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp))
                    .border(width = 1.dp, shape = RoundedCornerShape(0.dp), color = Color.Black)
                    .padding(6.dp),
                text = "Rate Order", color = Color.Black
            )
        }
    }
}