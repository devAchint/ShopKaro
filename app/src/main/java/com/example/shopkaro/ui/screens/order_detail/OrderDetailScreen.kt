package com.example.shopkaro.ui.screens.order_detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.shopkaro.R
import com.example.shopkaro.data.models.ShippingDetailModel
import com.example.shopkaro.ui.theme.GreenColor
import com.example.shopkaro.utils.formatOrderDate
import com.example.shopkaro.utils.totalCartPrice

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(orderDetailState: OrderDetailState) {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            if (orderDetailState.isLoading) {
                Text(text = "Loading...", modifier = Modifier.align(Alignment.Center))
            }
            orderDetailState.error?.let {
                Text(text = it)
            }
            orderDetailState.orderModel?.let { order ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    OrderedItem(
                        productName = "${order.items.size} Items",
                        productImage = order.items.map { it.productImage },
                        totalPrice = order.items.totalCartPrice()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    val sampleTrackingList = listOf(
                        "Ordered on ${formatOrderDate(order.date.toLong())}",
                        "Shipped on ${formatOrderDate(order.date.toLong())}",
                        "Delivered on ${formatOrderDate(order.date.toLong())}"
                    )
                    OrderTracking(steps = sampleTrackingList, currentStep = 1)
                    Spacer(modifier = Modifier.height(20.dp))
                    ShippingDetails(order.shippingDetails)
                    Spacer(modifier = Modifier.height(20.dp))
                    PriceDetails(order.items.totalCartPrice())
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }
}

@Composable
fun OrderedItem(productName: String, totalPrice: Double, productImage: List<String>) {
    Column {
        Text(text = "Order Details", fontSize = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = productName, maxLines = 2, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "₹$totalPrice", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.width(20.dp))
            OrderImageCollage(images = productImage)
        }
    }

}


@Composable
fun OrderTracking(steps: List<String>, currentStep: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Order Tracking", fontSize = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
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
        val iconId =
            if (isCompleted) R.drawable.check_circle_24 else if (isCurrent) R.drawable.location_on_24 else R.drawable.circle
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if (isCompleted) GreenColor else Color.Gray,
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
            .background(if (isCompleted) GreenColor else Color.Gray)
    )
}

@Composable
fun ShippingDetails(shippingModel: ShippingDetailModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Shipping Details", fontSize = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = shippingModel.name, fontSize = 18.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = shippingModel.houseNumber)
        Text(text = shippingModel.streetAddress)
        Text(text = shippingModel.city)
        Text(text = shippingModel.state)
        Text(text = "Phone Number ${shippingModel.phoneNumber}")
    }
}

@Composable
fun PriceDetails(totalCartPrice: Double) {
    Column {
        Text(text = "Price Details", fontSize = 12.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        PriceDetailItem("List Price", totalCartPrice)
        PriceDetailItem("Selling Price", totalCartPrice)
        PriceDetailItem("Delivery Charge", 0.0)
        PriceDetailItem("Total Amount", totalCartPrice)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Payment Mode : ")
    }

}

@Composable
fun PriceDetailItem(title: String, price: Double) {
    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
    ) {
        Text(text = title, modifier = Modifier.weight(1f))
        Text(text = "₹$price", modifier = Modifier.weight(1f), textAlign = TextAlign.End)
    }
}

@Composable
fun OrderImageCollage(images: List<String>) {
    val displayImages = if (images.size > 3) images.take(3) else images
    val extraImagesCount = images.size - 3

    Row {
        Column {
            OrderImage(image = displayImages.getOrNull(0), images.size)
            OrderImage(image = displayImages.getOrNull(1), images.size)
        }
        Column {
            if (images.size > 2) {
                OrderImage(image = displayImages.getOrNull(2), images.size)
            }
            if (extraImagesCount > 0) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(2.dp)

                ) {
                    Text(
                        text = "+$extraImagesCount",
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun OrderImage(image: String?, imageCount: Int) {
    val imageSize = when (imageCount) {
        1 -> {
            96.dp
        }

        else -> {
            48.dp
        }
    }
    image?.let {
        Image(
            rememberAsyncImagePainter(image),
            contentDescription = null,
            modifier = Modifier
                .size(imageSize)
                .padding(2.dp)
        )
    }
}
