package com.example.shopkaro.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shopkaro.graphs.BottomNavGraph
import com.example.shopkaro.graphs.BottomNavScreens
import com.example.shopkaro.graphs.CartScreens
import com.example.shopkaro.graphs.HomeScreens
import com.example.shopkaro.graphs.ProfileScreens


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(modifier = Modifier.statusBarsPadding(),
        bottomBar = {
            if (navBackStackEntry?.destination?.route == HomeScreens.HomeScreen.route
                || navBackStackEntry?.destination?.route == ProfileScreens.ProfileScreen.route
                || navBackStackEntry?.destination?.route == CartScreens.CartScreen.route
            ) {
                BottomBar(navController = navController)
            }
        }) { innerPadding ->
        BottomNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavScreens.HomeScreen,
        BottomNavScreens.CartScreen,
        BottomNavScreens.ProfileScreen
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = "") })
        }

    }
}