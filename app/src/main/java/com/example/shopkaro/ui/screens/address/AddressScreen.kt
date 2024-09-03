package com.example.shopkaro.ui.screens.address

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.shopkaro.data.models.ShippingDetailModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    addressUiState: AddressUiState,
    addOrder: (ShippingDetailModel) -> Unit,
    navigateToPayment: (String) -> Unit
) {
    val context = LocalContext.current
    if (addressUiState.addressProcessed && addressUiState.orderId != null) {
        navigateToPayment(addressUiState.orderId)
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Address") },
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
            var fullName by rememberSaveable { mutableStateOf("") }
            var phoneNumber by rememberSaveable { mutableStateOf("") }
            var city by rememberSaveable { mutableStateOf("") }
            var state by rememberSaveable { mutableStateOf("") }
            var houseNo by rememberSaveable { mutableStateOf("") }
            var street by rememberSaveable { mutableStateOf("") }

            val isAddressFilled = remember {
                derivedStateOf {
                    fullName.isNotBlank() && phoneNumber.isNotBlank() && city.isNotBlank() && state.isNotBlank() && houseNo.isNotBlank() && street.isNotBlank()
                }
            }

            OutlinedTextField(
                value = fullName,
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                onValueChange = {
                    fullName = it
                },
                placeholder = { Text(text = "Full Name") },
                shape = RoundedCornerShape(8.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = phoneNumber,
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                onValueChange = {
                    phoneNumber = it
                },
                placeholder = { Text(text = "Phone Number") },
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = city,
                    modifier = Modifier
                        .weight(1f),
                    onValueChange = {
                        city = it
                    },
                    singleLine = true,
                    placeholder = { Text(text = "City") },
                    shape = RoundedCornerShape(8.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))


                OutlinedTextField(
                    value = state,
                    modifier = Modifier
                        .weight(1f),
                    onValueChange = {
                        state = it
                    },
                    singleLine = true,
                    placeholder = { Text(text = "State") },
                    shape = RoundedCornerShape(8.dp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AddressInput(text = houseNo, onTextChange = { houseNo = it }, placeHolder = "House No")
            Spacer(modifier = Modifier.height(16.dp))

            AddressInput(
                text = street,
                onTextChange = { street = it },
                placeHolder = "Street Address"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (isAddressFilled.value) {
                        val shippingDetails = ShippingDetailModel(
                            name = fullName,
                            phoneNumber = phoneNumber,
                            city = city,
                            state = state,
                            houseNumber = houseNo,
                            streetAddress = street
                        )
                        addOrder(shippingDetails)
                    } else {
                        Toast.makeText(context, "Fill Required Details", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                val buttonText =
                    if (addressUiState.addressProcessing) "Loading" else "Go to Payment"
                Text(text = buttonText)
            }
        }

    }
}

@Composable
fun AddressInput(text: String, onTextChange: (String) -> Unit, placeHolder: String) {
    OutlinedTextField(
        value = text,
        modifier = Modifier
            .fillMaxWidth(),
        onValueChange = {
            onTextChange(it)
        },
        singleLine = true,
        placeholder = { Text(text = placeHolder) },
        shape = RoundedCornerShape(8.dp),
    )
}