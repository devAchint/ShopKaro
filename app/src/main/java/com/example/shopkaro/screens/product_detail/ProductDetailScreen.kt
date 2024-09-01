package com.example.shopkaro.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.shopkaro.R
import com.example.shopkaro.ui.theme.Star

@Composable
fun ProductDetailScreen(productDetailState: ProductDetailState,navigateToCart:()->Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Home") }, actions = {
                IconButton(onClick = { navigateToCart()}) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
                }
            })
        }
    )
    { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            if (productDetailState.isLoading) {
                Text(text = "Loading", modifier = Modifier.align(Alignment.Center))
            }
            productDetailState.product?.let { product ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = rememberAsyncImagePainter(product.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(RoundedCornerShape(12.dp))


                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = product.title,
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Save 20%",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Red)
                                    .padding(vertical = 2.dp, horizontal = 6.dp)
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
                                Text(
                                    text = "(${product.rating.count} Reviews)",
                                    color = Color.Black.copy(alpha = 0.5f)
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Information", color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = product.description, color = Color.Black.copy(alpha = 0.5f))
                    }
                    var quantity by rememberSaveable {
                        mutableIntStateOf(0)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "₹${product.price}", fontSize = 24.sp, color = Color.Black)
                        Button(
                            onClick = { if (quantity == 0) quantity += 1 },
                            modifier = Modifier
                                .height(48.dp),
                            shape = RoundedCornerShape(10.dp),
                        ) {
                            if (quantity != 0) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_minus), // Replace with your minus icon resource
                                        contentDescription = "Decrease quantity",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clickable { quantity -= 1 }

                                    )
                                    Text(
                                        text = "$quantity",
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_plus), // Replace with your minus icon resource
                                        contentDescription = "Decrease quantity",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clickable { quantity += 1 }
                                    )
                                }
                            } else {
                                Text(text = "Add to Cart", fontSize = 16.sp)
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            } ?: run {
                Text(text = "error")
            }
        }


    }
}