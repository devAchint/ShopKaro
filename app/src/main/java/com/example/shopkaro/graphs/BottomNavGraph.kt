package com.example.shopkaro.graphs

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.shopkaro.R

@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        route = Graph.BOTTOM,
        startDestination = Graph.HOME,
    ) {
        homeNavGraph(navController, modifier)
        profileNavGraph()
        cartNavGraph()
    }
}

sealed class BottomNavScreens(val route: String, @StringRes val resId: Int) {
    object HomeScreen : BottomNavScreens(Graph.HOME, R.string.home)
    object CartScreen : BottomNavScreens(Graph.CART, R.string.cart)
    object ProfileScreen : BottomNavScreens(Graph.PROFILE, R.string.profile)
}
