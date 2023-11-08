package com.mitocode.marketcomposeapp.presentation.component

import android.view.ViewGroup
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner

@Composable
fun CameraXComponent(
    modifier: Modifier = Modifier,
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner,
    visibilityComponents: Boolean
) {

    cameraController.bindToLifecycle(lifecycle)

    if (!visibilityComponents) {
        AndroidView(
            modifier = modifier,
            factory = {
                val previewView = PreviewView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
                previewView.controller = cameraController
                previewView
            })
    }
}