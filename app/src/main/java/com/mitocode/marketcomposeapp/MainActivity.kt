package com.mitocode.marketcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mitocode.marketcomposeapp.navigation.SetupNavGraph
import com.mitocode.marketcomposeapp.ui.theme.MarketComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketComposeAppTheme {

                val navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}