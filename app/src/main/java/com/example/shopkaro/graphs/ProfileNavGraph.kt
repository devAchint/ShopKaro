package com.example.shopkaro.graphs

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.OrderDetailScreen
import com.example.shopkaro.screens.OrdersScreen
import com.example.shopkaro.screens.profile.ProfileScreen
import com.example.shopkaro.screens.profile.ProfileViewModel

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreens.ProfileScreen.route,
    ) {
        composable(ProfileScreens.ProfileScreen.route) {
            val profileViewModel: ProfileViewModel = hiltViewModel()
            val profileUiState = profileViewModel.profileUiState.collectAsState()
            ProfileScreen(
                profileUiState = profileUiState.value,
                signOut = {
                    profileViewModel.signOut()
                },
                navigateToOrders = {
                    navController.navigate(ProfileScreens.OrdersScreen.route)
                }
            )
        }
        composable(ProfileScreens.OrdersScreen.route) {
            OrdersScreen(navigateToOrderDetail = {
                navController.navigate(ProfileScreens.OrderDetailScreen.route)
            })
        }
        composable(ProfileScreens.OrderDetailScreen.route) {
            OrderDetailScreen()
        }
    }
}

sealed class ProfileScreens(val route: String) {
    object ProfileScreen : ProfileScreens("profile")
    object OrdersScreen : ProfileScreens("orders")
    object OrderDetailScreen : ProfileScreens("order_detail")
}
