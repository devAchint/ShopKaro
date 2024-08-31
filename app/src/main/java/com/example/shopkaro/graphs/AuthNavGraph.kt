package com.example.shopkaro.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.LoginScreen
import com.example.shopkaro.screens.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthScreen.Login.route) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                navigateToHome = {
                    navController.navigate("main")
                }
            )
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(
                navigateToHome = {
                    navController.navigate("main")
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
}