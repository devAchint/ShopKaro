package com.example.shopkaro.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.shopkaro.screens.HomeScreen
import com.example.shopkaro.screens.ProductScreen
import com.example.shopkaro.screens.product_detail.ProductDetailScreen
import com.example.shopkaro.screens.product_detail.ProductDetailViewModel

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOME,
        startDestination = HomeScreens.HomeScreen.route,
    ) {
        composable(HomeScreens.HomeScreen.route) {
            HomeScreen(
                navigateToProduct = {id->
                    navController.navigate(HomeScreens.ProductDetailScreen.passArgs(id))
                }
            )
        }
        composable(HomeScreens.ProductScreen.route) { ProductScreen() }
        composable(HomeScreens.ProductDetailScreen.route, arguments = listOf(
            navArgument("productId") {
                type = NavType.IntType
            }
        )) {
            val productDetailViewModel: ProductDetailViewModel = hiltViewModel()
            val productDetailState = productDetailViewModel.productDetailUiState.collectAsState()
            ProductDetailScreen(productDetailState.value, navigateToCart = {
                navController.navigate(Graph.CART)
            })
        }
    }
}

sealed class HomeScreens(val route: String) {
    object HomeScreen : HomeScreens("home")
    object ProductScreen : HomeScreens("product")
    object ProductDetailScreen : HomeScreens("product_detail/{productId}") {
        fun passArgs(id: Int): String {
            return "product_detail/$id"
        }
    }
}
