package com.example.shopkaro.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.CartScreen
import com.example.shopkaro.screens.HomeScreen
import com.example.shopkaro.screens.ProductScreen
import com.example.shopkaro.screens.ProfileScreen

fun NavGraphBuilder.cartNavGraph() {
    navigation(
        route = Graph.CART,
        startDestination = CartScreens.CartScreen.route,
    ) {
        composable(CartScreens.CartScreen.route) { CartScreen() }
    }
}

sealed class CartScreens(val route: String) {
    object CartScreen: CartScreens("cart")

}
