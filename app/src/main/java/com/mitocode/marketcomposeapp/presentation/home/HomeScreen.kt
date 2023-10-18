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
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.mitocode.marketcomposeapp.navigation.ScreenHome
import com.mitocode.marketcomposeapp.navigation.SetupNavGraphHome
import com.mitocode.marketcomposeapp.presentation.component.AlertDialogComponent
import com.mitocode.marketcomposeapp.presentation.component.AppBarComponent
import com.mitocode.marketcomposeapp.presentation.component.BottomNavigationItem
import com.mitocode.marketcomposeapp.presentation.component.DialodCustomComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val items = listOf(
        BottomNavigationItem(
            title = "Categories",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            route = ScreenHome.Category.route
        ),
        BottomNavigationItem(
            title = "Locate",
            selectedIcon = Icons.Filled.Map,
            unselectedIcon = Icons.Outlined.Map,
            badgeCount = 40,
            hasNews = true,
            route = ScreenHome.Locate.route
        ),
        BottomNavigationItem(
            title = "Setting",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
            route = ScreenHome.Setting.route
        )
    )

    Box {

        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }

        var openDialog by remember {
            mutableStateOf(false)
        }

        var openDialogCustom by remember {
            mutableStateOf(false)
        }

        val navController = rememberNavController()

        AlertDialogComponent(
            showDialog = openDialog,
            title = "Mi primer Dialogo",
            dismissDialog = { openDialog = false }
        )

        DialodCustomComponent(
            showDialog = openDialogCustom,
            dismissDialog = { openDialogCustom = false }
        )

        Scaffold(
            topBar = {
                AppBarComponent(
                    title = "La tiendita",
                    onItemClick = { openDialog = true },
                    onPopClick = { openDialogCustom = true }
                )
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
                            selected = selectedItemIndex == index,
                            onClick = {
                                //onClick()
                                selectedItemIndex = index
                                navController.navigate(items.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    //launchSingleTop = true
                                }
                            },
                            label = {
                                Text(text = items.title)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (items.badgeCount != null) {
                                            Badge {
                                                Text(text = items.badgeCount.toString())
                                            }
                                        } else if (items.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            items.selectedIcon
                                        } else items.unselectedIcon,
                                        contentDescription = items.title
                                    )
                                }
                            })
                    }
                }
            }
        ) {
            SetupNavGraphHome(
                navController = navController,
                padding = it
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}