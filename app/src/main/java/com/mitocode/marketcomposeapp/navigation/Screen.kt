package com.mitocode.marketcomposeapp.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash_screen")

    object Login : Screen("login_screen")

    object Home : Screen("home_screen")

    object Category : Screen("category_screen")
}
