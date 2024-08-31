package com.example.shopkaro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.shopkaro.graphs.RootNavGraph
import com.example.shopkaro.ui.theme.ShopKaroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopKaroTheme {
                val navController = rememberNavController()
                RootNavGraph(navController = navController)
            }
        }
    }
}

//@Composable
//fun App(navController: NavHostController, modifier: Modifier = Modifier) {
//    NavHost(
//        navController = navController,
//        startDestination = Screens.MainScreen.route,
//        modifier = modifier,
//        enterTransition = { enterTrans() },
//        exitTransition = { exitTrans() },
//        popEnterTransition = { popEnterTrans() },
//        popExitTransition = { popExitTrans() }
//    ) {
//        composable(
//            route = Screens.MainScreen.route
//        ) {
//            val lifecycleOwner = LocalLifecycleOwner.current
//
//        }
//
//        composable(route = Screens.ProductScreen.route) {
//
//        }
//        composable(route = Screens.CategoryScreen.route) {
//
//        }
//        composable(route = Screens.LoginScreen.route) {
//
//        }
//        composable(route = Screens.RegisterScreen.route) {
//
//        }
//    }
//}