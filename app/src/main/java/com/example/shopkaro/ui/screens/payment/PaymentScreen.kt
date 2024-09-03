package com.example.shopkaro.ui.screens.payment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopkaro.R
import com.example.shopkaro.data.models.PaymentMethodModel
import com.example.shopkaro.ui.theme.BoxColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    paymentUiState: PaymentUiState,
    changePaymentMethod: (String) -> Unit,
    placeOrder: () -> Unit,
    navigateToOrderPlaced: () -> Unit
) {
    val context = LocalContext.current
    paymentUiState.error?.let {
        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        Log.d("MYDEBUG", it.toString())
    }

    if (paymentUiState.paymentProcessed) {
        navigateToOrderPlaced()
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Payment") },
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
                .padding(16.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            val paymentMethods =
                listOf(
                    PaymentMethodModel(R.drawable.cash_icon, "Cash on delivery"),
                    PaymentMethodModel(R.drawable.credit_card_icon, "Credit/Debit Card"),
                    PaymentMethodModel(R.drawable.bank_icon, "Bank Transfer"),
                    PaymentMethodModel(R.drawable.upi_icon, "Upi"),
                )
            val selectedPaymentMethod by rememberUpdatedState(paymentUiState.paymentMethod)

            Column {
                Text(text = "Choose Payment method")
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(paymentMethods) {
                        PaymentMethod(
                            icon = it.icon,
                            methodName = it.methodName,
                            selected = selectedPaymentMethod
                        ) {
                            changePaymentMethod(it)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            Button(
                onClick = {
                    placeOrder()
                },
                enabled = !paymentUiState.paymentProcessing,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                val buttonText =
                    if (paymentUiState.paymentProcessing) "Loading" else "Confirm Payment"
                Text(text = buttonText, fontSize = 16.sp)
            }
        }

    }
}


@Composable
fun PaymentMethod(
    icon: Int,
    methodName: String,
    selected: String,
    onSelectedChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(BoxColor)
            .clickable { onSelectedChange(methodName) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = icon), contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = methodName, fontSize = 16.sp, color = Color.Black)
        }

        RadioButton(selected = methodName == selected, onClick = { onSelectedChange(methodName) })
    }

}