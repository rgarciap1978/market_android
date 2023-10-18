package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialodCustomComponent(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    dismissDialog: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { dismissDialog() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = modifier,
                shape = RoundedCornerShape(10.dp),
                shadowElevation = 20.dp,
                tonalElevation = 14.dp
                //border = BorderStroke(
                //    width = 1.dp,
                //    color = Color.Black
                //)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(text = "Line 1")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Line 2")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Line 3")
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(onClick = {
                        dismissDialog()
                    }) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}