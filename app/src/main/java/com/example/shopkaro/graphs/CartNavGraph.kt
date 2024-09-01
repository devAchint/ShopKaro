package com.example.shopkaro.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.AddressScreen
import com.example.shopkaro.screens.CartScreen
import com.example.shopkaro.screens.OrderPlacedScreen
import com.example.shopkaro.screens.PaymentScreen

fun NavGraphBuilder.cartNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CART,
        startDestination = CartScreens.CartScreen.route,
    ) {
        composable(CartScreens.CartScreen.route) {
            CartScreen(navigateToAddress = {
                navController.navigate(CartScreens.AddressScreen.route)
            })
        }
        composable(CartScreens.AddressScreen.route) {
            AddressScreen(navigateToPayment = {
                navController.navigate(CartScreens.PaymentScreen.route)
            })
        }
        composable(CartScreens.PaymentScreen.route) {
            PaymentScreen(
                navigateToOrderPlaced = {
                    navController.navigate(CartScreens.OrderPlacedScreen.route)
                }
            )
        }
        composable(CartScreens.OrderPlacedScreen.route) { OrderPlacedScreen() }
    }
}

sealed class CartScreens(val route: String) {
    object CartScreen : CartScreens("cart")
    object AddressScreen : CartScreens("address")
    object PaymentScreen : CartScreens("payment")
    object OrderPlacedScreen : CartScreens("order_placed")
}
