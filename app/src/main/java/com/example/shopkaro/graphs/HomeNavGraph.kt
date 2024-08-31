package com.example.shopkaro.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.HomeScreen
import com.example.shopkaro.screens.ProductScreen

fun NavGraphBuilder.homeNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        route = Graph.HOME,
        startDestination = HomeScreens.HomeScreen.route,
    ) {
        composable(HomeScreens.HomeScreen.route) {
            HomeScreen(modifier = modifier,
                navigateToProduct = {
                    navController.navigate(HomeScreens.ProductScreen.route)
                })
        }
        composable(HomeScreens.ProductScreen.route) { ProductScreen() }
    }
}

sealed class HomeScreens(val route: String) {
    object HomeScreen : HomeScreens("home")
    object ProductScreen : HomeScreens("product")
}
