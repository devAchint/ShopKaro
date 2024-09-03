package com.example.shopkaro.ui.screens.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(
    registerUiState: RegisterUiState,
    registerUser: (email: String, password: String) -> Unit,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    val context = LocalContext.current
    if (registerUiState.success) {
        Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
        navigateToHome()
    }
    registerUiState.errorMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Sign Up",
                fontSize = 32.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            var emailText by rememberSaveable { mutableStateOf("") }

            TextField(
                value = emailText, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onValueChange = {
                    emailText = it
                },
                singleLine = true,
                placeholder = { Text(text = "Email") },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            var passwordText by rememberSaveable { mutableStateOf("") }

            TextField(
                value = passwordText, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onValueChange = {
                    passwordText = it
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text(text = "Password") },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))
            var confirmPasswordText by rememberSaveable { mutableStateOf("") }
            val passwordMatched = remember {
                derivedStateOf {
                    passwordText == confirmPasswordText
                }
            }
            TextField(
                value = confirmPasswordText, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onValueChange = {
                    confirmPasswordText = it
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text(text = "Confirm Password") },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            if (!passwordMatched.value) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Password not matched",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (emailText.isNotEmpty() && passwordMatched.value) {
                        registerUser(emailText, confirmPasswordText)
                    }
                },
                enabled = registerUiState.isLoading.not(),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                if (registerUiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.5.dp)
                } else {
                    Text(text = "Sign Up", fontSize = 18.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(modifier = Modifier.clickable { navigateToLogin() },
                text = buildAnnotatedString {
                    append("Already have an account? ")

                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("Login Now")
                    }
                }
            )
        }
    }
}