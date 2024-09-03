package com.example.shopkaro.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.ui.screens.OrderPlacedScreen
import com.example.shopkaro.ui.screens.address.AddressScreen
import com.example.shopkaro.ui.screens.address.AddressViewModel
import com.example.shopkaro.ui.screens.cart.CartScreen
import com.example.shopkaro.ui.screens.cart.CartViewModel
import com.example.shopkaro.ui.screens.payment.PaymentScreen
import com.example.shopkaro.ui.screens.payment.PaymentViewModel

fun NavGraphBuilder.cartNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        route = Graph.CART,
        startDestination = CartScreens.CartScreen.route,
    ) {
        composable(CartScreens.CartScreen.route) {
            val cartViewModel: CartViewModel = hiltViewModel()
            val cartUiState = cartViewModel.cartUiState.collectAsState()
            CartScreen(
                modifier = modifier,
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
                    addressViewModel.resetAddressUiState()
                }
            )
        }
        composable(CartScreens.PaymentScreen.route) {
            val paymentViewModel: PaymentViewModel = hiltViewModel()
            val paymentUiState = paymentViewModel.paymentUiState.collectAsState()
            val orderId = it.arguments?.getString("orderId")
            PaymentScreen(
                paymentUiState = paymentUiState.value,
                changePaymentMethod = {
                    paymentViewModel.changePaymentMethod(it)
                },
                placeOrder = {
                    paymentViewModel.placeOrder()
                    paymentViewModel.resetPaymentUiState()
                },
                navigateToOrderPlaced = {
                    orderId?.let {
                        navController.navigate(CartScreens.OrderPlacedScreen.passArgs(it))
                    }
                }
            )
        }
        composable(CartScreens.OrderPlacedScreen.route) {
            val orderId = it.arguments?.getString("orderId")
            OrderPlacedScreen(
                navigateToHome = {
                    navController.popBackStack(
                        route = HomeScreens.HomeScreen.route,
                        inclusive = false
                    )
                },
                navigateToOrderDetail = {
                    orderId?.let {
                        navController.navigate(ProfileScreens.OrderDetailScreen.passArgs(it))
                    }
                }
            )
        }
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

    object OrderPlacedScreen : CartScreens("order_placed/{orderId}") {
        fun passArgs(orderId: String): String {
            return "order_placed/$orderId"
        }
    }
}
