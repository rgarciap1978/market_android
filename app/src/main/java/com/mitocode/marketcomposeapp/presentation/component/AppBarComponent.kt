package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotificationImportant
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mitocode.marketcomposeapp.ui.theme.PrimaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarComponent(
    modifier: Modifier = Modifier,
    title: String,
    navController: NavHostController,
    onItemClick: () -> Unit,
    onPopClick: () -> Unit,
    onBackClick: () -> Unit
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val showBackButton by remember(currentBackStackEntry) {
        derivedStateOf {
            navController.previousBackStackEntry != null
        }
    }

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = PrimaryLight
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    onBackClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Information",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clickable {
                        onItemClick()
                    }
            )
            Icon(
                imageVector = Icons.Filled.NotificationImportant,
                contentDescription = "Notify",
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clickable {
                        onPopClick()
                    }
            )
        })
}