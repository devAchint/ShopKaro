package com.example.shopkaro.ui.screens.product_detail

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
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
import com.example.shopkaro.data.models.ProductResponse
import com.example.shopkaro.data.models.Rating
import com.example.shopkaro.ui.theme.Star

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productDetailState: ProductDetailState,
    navigateToCart: () -> Unit,
    addToCart: (productId: Int) -> Unit,
    removeFromCart: (productId: Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Detail") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navigateToCart() }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
                    }
                }
            )
        }
    )
    { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {

            productDetailState.error?.let {
                Text(text = it, modifier = Modifier.align(Alignment.Center))
            }
            if (productDetailState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp).align(Alignment.Center), strokeWidth = 2.5.dp)
            } else {
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
                            CouponAndRating(rating = product.rating)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Information", color = Color.Black)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = product.description, color = Color.Black.copy(alpha = 0.5f))
                        }

                        AddCartLayout(
                            addToCart = addToCart,
                            removeFromCart = removeFromCart,
                            product = product,
                            productDetailState = productDetailState
                        )
                    }
                } ?: run {
                    Text(text = "error")
                }
            }
        }

    }
}

@Composable
fun CouponAndRating(rating: Rating) {
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
                .padding(vertical = 2.dp, horizontal = 6.dp),
            color = Color.White
        )
        Row {
            Icon(
                Icons.Filled.Star, contentDescription = null, tint = Star,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "${rating.rate}", color = Color.Black)
            Text(
                text = "(${rating.count} Reviews)",
                color = Color.Black.copy(alpha = 0.5f)
            )
        }

    }
}

@Composable
fun AddCartLayout(
    addToCart: (productId: Int) -> Unit,
    removeFromCart: (productId: Int) -> Unit,
    product: ProductResponse,
    productDetailState: ProductDetailState
) {
    val quantity = rememberUpdatedState(productDetailState.cartQuantity)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "₹${product.price}", fontSize = 24.sp, color = Color.Black)
        Button(
            onClick = {
                if (quantity.value == 0) {
                    //quantity += 1
                    addToCart(product.id)
                }
            },
            modifier = Modifier
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            if (quantity.value != 0) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_minus), // Replace with your minus icon resource
                        contentDescription = "Decrease quantity",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                // quantity -= 1
                                removeFromCart(product.id)
                            }

                    )
                    Text(
                        text = "${quantity.value}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus), // Replace with your minus icon resource
                        contentDescription = "Decrease quantity",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                //  quantity += 1
                                addToCart(product.id)
                            }
                    )
                }
            } else {
                Text(text = "Add to Cart", fontSize = 16.sp)
            }
        }

    }
    Spacer(modifier = Modifier.height(16.dp))
}