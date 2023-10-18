package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AlertDialogComponent(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    title: String,
    dismissDialog: () -> Unit
) {
    if (showDialog) {

        AlertDialog(
            modifier = modifier,
            onDismissRequest = { dismissDialog() },
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
            },
            confirmButton = {
                Button(onClick = { dismissDialog() }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = { dismissDialog() }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}