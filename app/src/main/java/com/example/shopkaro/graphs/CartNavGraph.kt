package com.example.shopkaro.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.CartScreen

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
