package com.mitocode.marketcomposeapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mitocode.marketcomposeapp.presentation.category.CategoryScreen
import com.mitocode.marketcomposeapp.presentation.locate.LocateScreen
import com.mitocode.marketcomposeapp.presentation.setting.SettingScreen

@Composable
fun SetupNavGraphHome(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = ScreenHome.Category.route
    ) {
        composable(route = ScreenHome.Category.route) {
            CategoryScreen(padding = padding)
        }

        composable(route = ScreenHome.Locate.route) {
            LocateScreen(padding)
        }

        composable(route = ScreenHome.Setting.route) {
            SettingScreen(padding)
        }
    }

}