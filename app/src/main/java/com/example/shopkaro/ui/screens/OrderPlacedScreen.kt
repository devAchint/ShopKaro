package com.example.shopkaro.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shopkaro.R


@Composable
fun OrderPlacedScreen(navigateToHome: () -> Unit, navigateToOrderDetail: () -> Unit) {
    BackHandler {
        navigateToHome()
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.order_success))
    Scaffold { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp), Arrangement.Center, Alignment.CenterHorizontally
        ) {
            LottieAnimation(modifier = Modifier.size(320.dp), composition = composition)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Order Placed Successfully", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Order is successfully placed\nand will be delivered soon",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    navigateToOrderDetail()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Track Order Status")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navigateToHome()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Back to Home")
            }
        }

    }
}

