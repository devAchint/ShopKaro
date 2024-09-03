package com.example.shopkaro.navigation.graphs

import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.ui.screens.order_detail.OrderDetailScreen
import com.example.shopkaro.ui.screens.order_detail.OrderDetailViewModel
import com.example.shopkaro.ui.screens.orders.OrdersScreen
import com.example.shopkaro.ui.screens.orders.OrdersViewModel
import com.example.shopkaro.ui.screens.profile.ProfileScreen
import com.example.shopkaro.ui.screens.profile.ProfileViewModel

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreens.ProfileScreen.route,
    ) {

        composable(ProfileScreens.ProfileScreen.route) {
            val profileViewModel: ProfileViewModel = hiltViewModel()
            val profileUiState = profileViewModel.profileUiState.collectAsState()
            val context = LocalContext.current
            ProfileScreen(
                profileUiState = profileUiState.value,
                signOut = {
                    profileViewModel.signOut()
                    if (context is ComponentActivity) {
                        context.finishAffinity()
                    }
                },
                navigateToOrders = {
                    navController.navigate(ProfileScreens.OrdersScreen.route)
                }
            )
        }
        composable(ProfileScreens.OrdersScreen.route) {
            val orderViewModel: OrdersViewModel = hiltViewModel()
            val ordersUiState = orderViewModel.orderUiState.collectAsState()
            OrdersScreen(
                ordersUiState = ordersUiState.value,
                navigateToOrderDetail = { orderId ->
                    navController.navigate(ProfileScreens.OrderDetailScreen.passArgs(orderId))
                }
            )
        }
        composable(ProfileScreens.OrderDetailScreen.route) {
            val orderDetailViewModel: OrderDetailViewModel = hiltViewModel()
            val orderDetailUiState = orderDetailViewModel.orderDetailUiState.collectAsState()

            OrderDetailScreen(orderDetailState = orderDetailUiState.value)
        }
    }
}

sealed class ProfileScreens(val route: String) {
    object ProfileScreen : ProfileScreens("profile")
    object OrdersScreen : ProfileScreens("orders")
    object OrderDetailScreen : ProfileScreens("order_detail/{orderId}") {
        fun passArgs(orderId: String): String {
            return "order_detail/$orderId"
        }
    }
}
