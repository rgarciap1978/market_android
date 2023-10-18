package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NotificationImportant
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitocode.marketcomposeapp.ui.theme.PrimaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarComponent(
    modifier: Modifier = Modifier,
    title: String,
    onItemClick: () -> Unit,
    onPopClick: ()->Unit
) {
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