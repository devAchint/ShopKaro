package com.example.shopkaro.graphs

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
        profileNavGraph(modifier)
        cartNavGraph(navController)
    }
}

sealed class BottomNavScreens(val route: String, @StringRes val resId: Int, val icon: ImageVector) {
    object HomeScreen : BottomNavScreens(Graph.HOME, R.string.home, Icons.Filled.Home)
    object CartScreen : BottomNavScreens(Graph.CART, R.string.cart, Icons.Filled.ShoppingCart)
    object ProfileScreen : BottomNavScreens(Graph.PROFILE, R.string.profile, Icons.Filled.Person)
}
