package com.example.shopkaro

sealed class Screens(val route: String) {
    data object MainScreen : Screens("main")
    data object ProductScreen : Screens("product")
    data object CategoryScreen : Screens("category")
    data object LoginScreen : Screens("login")
    data object RegisterScreen : Screens("register")
}