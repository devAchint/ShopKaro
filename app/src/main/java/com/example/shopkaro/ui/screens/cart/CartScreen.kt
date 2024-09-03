package com.example.shopkaro.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import coil.compose.rememberAsyncImagePainter
import com.example.shopkaro.R
import com.example.shopkaro.data.models.CartModel
import com.example.shopkaro.ui.theme.BoxColor
import com.example.shopkaro.utils.totalCartPrice
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier,
    cartUiState: CartUiState,
    navigateToAddress: () -> Unit,
    addToCart: (productId: Int) -> Unit,
    removeFromCart: (productId: Int) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "My Cart") }
        )
    }) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)

        ) {
            if (cartUiState.isLoading) {
                Text(text = "Loading...", modifier = Modifier.align(Alignment.Center))
            } else {
                if (cartUiState.carts.isEmpty()) {
                    EmptyCart(modifier = Modifier.align(Alignment.Center))
                } else {
                    Column {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(cartUiState.carts) {
                                CartItem(cart = it, addToCart, removeFromCart)
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        CheckOut(cartUiState.carts.totalCartPrice()) {
                            navigateToAddress()
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun CartItem(
    cart: CartModel,
    addToCart: (productId: Int) -> Unit,
    removeFromCart: (productId: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                rememberAsyncImagePainter(cart.productImage), contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .padding(8.dp)

            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = cart.productName.take(20), fontSize = 16.sp, color = Color.Black)
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
                            .background(BoxColor)
                            .padding(4.dp)
                            .clickable {
                                removeFromCart(cart.productId)
                            },
                        tint = Color.Black,
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = ""
                    )
                    Text(
                        text = cart.productQuantity.toString(),
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(BoxColor)
                            .padding(4.dp)
                            .clickable {
                                addToCart(cart.productId)
                            },
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
            Text(text = "₹${cart.productPrice}", color = Color.Black)
            Text(text = "x ${cart.productQuantity}", color = Color.Black)
            Text(
                text = "₹${
                    BigDecimal(cart.productPrice * cart.productQuantity).setScale(
                        2,
                        RoundingMode.HALF_UP
                    ).toDouble()
                }",
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }

}

@Composable
fun CheckOut(cartValue: Double, navigateToAddress: () -> Unit) {
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
                text = "₹$cartValue",
                color = Color.Black,
                fontSize = 18.sp,
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