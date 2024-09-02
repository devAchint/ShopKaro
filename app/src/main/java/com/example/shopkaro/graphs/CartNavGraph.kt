package com.example.shopkaro.graphs

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.OrderPlacedScreen
import com.example.shopkaro.screens.address.AddressScreen
import com.example.shopkaro.screens.address.AddressViewModel
import com.example.shopkaro.screens.cart.CartScreen
import com.example.shopkaro.screens.cart.CartViewModel
import com.example.shopkaro.screens.payment.PaymentScreen
import com.example.shopkaro.screens.payment.PaymentViewModel

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
            val addressViewModel: AddressViewModel = hiltViewModel()
            val addressUiState = addressViewModel.addressUiState.collectAsState()
            AddressScreen(
                addressUiState = addressUiState.value,
                addOrder = {
                    addressViewModel.addOrder(it)
                },
                navigateToPayment = { orderId ->
                    navController.navigate(CartScreens.PaymentScreen.passArgs(orderId))
                }
            )
        }
        composable(CartScreens.PaymentScreen.route) {
            val paymentViewModel: PaymentViewModel = hiltViewModel()
            val paymentUiState = paymentViewModel.paymentUiState.collectAsState()
            PaymentScreen(
                paymentUiState = paymentUiState.value,
                changePaymentMethod = {
                    paymentViewModel.changePaymentMethod(it)
                },
                placeOrder = {
                    paymentViewModel.placeOrder()
                },
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
    object PaymentScreen : CartScreens("payment/{orderId}") {
        fun passArgs(orderId: String): String {
            return "payment/$orderId"
        }
    }

    object OrderPlacedScreen : CartScreens("order_placed")
}
