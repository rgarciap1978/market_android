package com.mitocode.marketcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mitocode.marketcomposeapp.presentation.category.CategoryScreen
import com.mitocode.marketcomposeapp.presentation.home.HomeScreen
import com.mitocode.marketcomposeapp.presentation.login.LoginScreen
import com.mitocode.marketcomposeapp.presentation.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    )
    {
        composable(route = Screen.Splash.route) {
            SplashScreen() {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            }
        }

        composable(route = Screen.Login.route) {
            LoginScreen() {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }

        composable(route = Screen.Home.route) {
            HomeScreen(){
                navController.navigate(Screen.Category.route)
            }
        }

        composable(route = Screen.Category.route) {
            CategoryScreen()
        }
    }

}