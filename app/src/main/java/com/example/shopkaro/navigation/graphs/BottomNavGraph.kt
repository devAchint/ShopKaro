package com.example.shopkaro.navigation.graphs

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
import com.example.shopkaro.utils.enterTrans
import com.example.shopkaro.utils.exitTrans
import com.example.shopkaro.utils.popEnterTrans
import com.example.shopkaro.utils.popExitTrans

@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        route = Graph.BOTTOM,
        startDestination = Graph.HOME
    ) {
        homeNavGraph(navController,modifier)
        profileNavGraph(navController)
        cartNavGraph(navController,modifier)
    }
}

sealed class BottomNavScreens(val route: String, val title: String, val icon: ImageVector) {
    object HomeScreen : BottomNavScreens(Graph.HOME, "Home", Icons.Filled.Home)
    object CartScreen : BottomNavScreens(Graph.CART, "Cart", Icons.Filled.ShoppingCart)
    object ProfileScreen : BottomNavScreens(Graph.PROFILE, "Profile", Icons.Filled.Person)
}
