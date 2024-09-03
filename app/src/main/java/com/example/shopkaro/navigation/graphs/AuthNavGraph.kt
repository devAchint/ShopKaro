package com.example.shopkaro.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.ui.screens.LoginScreen
import com.example.shopkaro.ui.screens.register.RegisterScreen
import com.example.shopkaro.ui.screens.register.RegisterViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthScreen.Login.route) {
        composable(route = AuthScreen.Login.route) {
            val loginViewModel: RegisterViewModel = hiltViewModel()
            val loginUiState = loginViewModel.registerUiState.collectAsState()
            LoginScreen(
                loginUiState = loginUiState.value,
                navigateToRegister = {
                    navController.navigate(AuthScreen.Register.route)
                }, navigateToHome = {
                    navController.navigate("main") {
                        popUpTo(AuthScreen.Login.route) {
                            inclusive = true
                        }
                    }
                    loginViewModel.resetRegisterState()
                }, login = { email, password ->
                    loginViewModel.loginUser(email, password)
                }
            )
        }
        composable(route = AuthScreen.Register.route) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            val registerUiState = registerViewModel.registerUiState.collectAsState()
            RegisterScreen(registerUiState.value,
                registerUser = { email, password ->
                    registerViewModel.registerUser(
                        email,
                        password
                    )
                },
                navigateToHome = {
                    navController.navigate("main")
                    // navController.popBackStack(destinationId = AuthScreen.Login, inclusive = true)
                },
                navigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
}