package com.example.shopkaro.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navigateToPayment: () -> Unit) {
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
            var phoneNumber by rememberSaveable { mutableStateOf("") }

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
                var city by rememberSaveable { mutableStateOf("") }

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
                var state by rememberSaveable { mutableStateOf("") }

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
            var houseNo by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = houseNo,
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = {
                    houseNo = it
                },
                singleLine = true,
                placeholder = { Text(text = "House Number") },
                shape = RoundedCornerShape(8.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            var street by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = street,
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = {
                    street = it
                },
                singleLine = true,
                placeholder = { Text(text = "Street Name") },
                shape = RoundedCornerShape(8.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navigateToPayment() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Go to Payment")
            }
        }

    }

}