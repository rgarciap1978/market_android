package com.mitocode.marketcomposeapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mitocode.marketcomposeapp.presentation.component.AppBarComponent
import com.mitocode.marketcomposeapp.presentation.component.BottomNavigationItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: () -> Unit
) {

    val context = LocalContext.current
    val items = listOf(
        BottomNavigationItem(
            title = "Categories",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            route = ""
        ),
        BottomNavigationItem(
            title = "Locate",
            selectedIcon = Icons.Filled.Map,
            unselectedIcon = Icons.Outlined.Map,
            badgeCount = 40,
            hasNews = true,
            route = ""
        ),
        BottomNavigationItem(
            title = "Setting",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
            route = ""
        )
    )

    Box {
        Scaffold(
            topBar = {
                AppBarComponent(title = "La tiendita")
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Toast.makeText(context, "Código QR Generado", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = Icons.Filled.QrCode2,
                        contentDescription = "QR Code"
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, items ->
                        NavigationBarItem(
                            selected = false,
                            onClick = {
                                onClick()
                            },
                            label = {
                                Text(text = items.title)
                            },
                            icon = {
                                Icon(
                                    imageVector = items.selectedIcon,
                                    contentDescription = items.title
                                )
                            })
                    }
                }
            }
        ) {
            val x = it.toString()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen() {}
}