package com.example.shopkaro.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.HomeScreen
import com.example.shopkaro.screens.ProductScreen
import com.example.shopkaro.screens.ProfileScreen

fun NavGraphBuilder.profileNavGraph() {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreens.ProfileScreen.route,
    ) {
        composable(ProfileScreens.ProfileScreen.route) { ProfileScreen() }
    }
}

sealed class ProfileScreens(val route: String) {
    object ProfileScreen : ProfileScreens("profile")
    object SettingsScreen : ProfileScreens("settings")
}
