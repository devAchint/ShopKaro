package com.example.shopkaro.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shopkaro.ui.screens.MainScreen
import com.google.firebase.auth.FirebaseUser


@Composable
fun RootNavGraph(navController: NavHostController, currentUser: FirebaseUser?) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if (currentUser != null) "main" else Graph.AUTHENTICATION,
    ) {
        authNavGraph(navController)
        composable(route = "main") {
            MainScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val BOTTOM = "bottom_graph"
    const val HOME = "home_graph"
    const val PROFILE = "profile_graph"
    const val CART = "cart_graph"
}