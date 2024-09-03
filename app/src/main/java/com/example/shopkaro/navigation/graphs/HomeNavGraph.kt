package com.example.shopkaro.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.shopkaro.ui.screens.HomeScreen
import com.example.shopkaro.ui.screens.product_detail.ProductDetailScreen
import com.example.shopkaro.ui.screens.product_detail.ProductDetailViewModel

fun NavGraphBuilder.homeNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        route = Graph.HOME,
        startDestination = HomeScreens.HomeScreen.route,
    ) {
        composable(HomeScreens.HomeScreen.route) {
            HomeScreen(
                modifier = modifier,
                navigateToProduct = { id ->
                    navController.navigate(HomeScreens.ProductDetailScreen.passArgs(id))
                }
            )
        }
        composable(
            HomeScreens.ProductDetailScreen.route, arguments = listOf(
            navArgument("productId") {
                type = NavType.IntType
            }
        )) {
            val productDetailViewModel: ProductDetailViewModel = hiltViewModel()
            val productDetailState = productDetailViewModel.productDetailUiState.collectAsState()
            ProductDetailScreen(
                productDetailState = productDetailState.value,
                navigateToCart = {
                    navController.navigate(Graph.CART)
                },
                addToCart = { productId -> productDetailViewModel.addToCart(productId) },
                removeFromCart = { productId -> productDetailViewModel.removeFromCart(productId) }
            )
        }
    }
}

sealed class HomeScreens(val route: String) {
    object HomeScreen : HomeScreens("home")
    object ProductDetailScreen : HomeScreens("product_detail/{productId}") {
        fun passArgs(id: Int): String {
            return "product_detail/$id"
        }
    }
}
