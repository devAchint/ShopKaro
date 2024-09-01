package com.example.shopkaro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopkaro.R
import com.example.shopkaro.ui.theme.BoxColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navigateToAddress: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "My Cart") }, navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = ""
                )
            }
        })
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            EmptyCart(modifier = Modifier.align(Alignment.Center))
            CheckOut(navigateToAddress)
        }
    }
}

@Composable
fun CartItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(BoxColor)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                Icons.Filled.ShoppingCart, contentDescription = null,
                modifier = Modifier
                    .width(48.dp)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Product name", fontSize = 16.sp, color = Color.Black)
                Text(
                    text = "category name",
                    fontSize = 12.sp,
                    color = Color.Black.copy(alpha = 0.5f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(4.dp),
                        tint = Color.Black,
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = ""
                    )
                    Text(
                        text = "0",
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(4.dp),
                        tint = Color.Black,
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = ""
                    )
                }
            }

        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "1,84,000", color = Color.Black)
            Text(text = "x 1", color = Color.Black)
            Text(
                text = "1,84,000",
                fontSize = 16.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun CheckOut(navigateToAddress: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BoxColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(text = "Total:", color = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "₹2,00,000",
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { navigateToAddress() },
            modifier = Modifier
                .height(48.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Continue", fontSize = 16.sp)
        }
    }
}

@Composable
fun EmptyCart(modifier: Modifier) {
    Text(text = "You haven't added any item in your cart ", fontSize = 16.sp, modifier = modifier)
}