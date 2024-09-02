package com.example.shopkaro.graphs

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.AddressScreen
import com.example.shopkaro.screens.OrderPlacedScreen
import com.example.shopkaro.screens.PaymentScreen
import com.example.shopkaro.screens.cart.CartScreen
import com.example.shopkaro.screens.cart.CartViewModel

fun NavGraphBuilder.cartNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CART,
        startDestination = CartScreens.CartScreen.route,
    ) {
        composable(CartScreens.CartScreen.route) {
            val cartViewModel: CartViewModel = hiltViewModel()
            val cartUiState = cartViewModel.cartUiState.collectAsState()
            CartScreen(
                cartUiState = cartUiState.value,
                navigateToAddress = {
                    navController.navigate(CartScreens.AddressScreen.route)
                },
                addToCart = { productId ->
                    cartViewModel.addToCart(productId)
                },
                removeFromCart = { productId ->
                    cartViewModel.removeFromCart(productId)
                }
            )
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
