package com.example.shopkaro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OrderDetailScreen() {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Order Details") },
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
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OrderTracking(steps = listOf("Ordered on 10 Nov", "shipped on 12 Nov"), currentStep = 1)
        }
    }
}

@Composable
fun OrderedItem() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Product Name product anme fldjss fjsdfk s", maxLines = 2, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "7888", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            Icons.Filled.Face, contentDescription = null,
            modifier = Modifier
                .size(48.dp)
        )
    }
}


@Composable
fun OrderTracking(steps: List<String>, currentStep: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        steps.forEachIndexed { index, step ->
            OrderStep(
                step = step,
                isCompleted = index < currentStep,
                isCurrent = index == currentStep
            )
            if (index < steps.size - 1) {
                OrderStepConnector(isCompleted = index < currentStep)
            }
        }
    }
}

@Composable
fun OrderStep(step: String, isCompleted: Boolean, isCurrent: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.AddCircle,
            contentDescription = null,
            tint = if (isCompleted) Color.Green else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = step,
            color = if (isCurrent) Color.Black else Color.Gray,
            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun OrderStepConnector(isCompleted: Boolean) {
    Box(
        modifier = Modifier
            .padding(start = 11.dp) // Center the line with the icon
            .height(40.dp)
            .width(2.dp)
            .background(if (isCompleted) Color.Green else Color.Gray)
    )
}

@Composable
fun ShippingDetails() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Shipping Details")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Achint Wadhwa", fontSize = 18.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "House no 455/54")
        Text(text = "Panipat")
        Text(text = "Haryana")
        Text(text = "Phone Number 9356566655")
    }
}