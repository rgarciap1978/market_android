package com.mitocode.marketcomposeapp.presentation.setting

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.Executor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(padding: PaddingValues) {

    val context = LocalContext.current

    var stateUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        println(it)
        stateUri = it
    }

    val cameraPhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val data = it.data?.extras?.get("data") as Bitmap
        var uri = bitmapToUri(data, context)
        stateUri = uri
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) {
        when {
            it -> {
                cameraPhotoLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            }

            else -> {
                println("Permiso denegado")
            }

        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Nombre")
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {}
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        cameraPermissionLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                    }
                ) {
                    Text(text = "Camara")
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        singlePhotoLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                ) {
                    Text(text = "Galeria")
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data(stateUri)
                        .crossfade(2000)
                        .build(),
                    contentDescription = "Imagen",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }


        }

    }
}

fun bitmapToUri(
    bitmap: Bitmap,
    context: Context
): Uri? {
    val byteArrayOutput = ByteArrayOutputStream()
    bitmap.compress(
        Bitmap.CompressFormat.JPEG,
        80,
        byteArrayOutput
    )

    val path = MediaStore.Images.Media.insertImage(
        context.contentResolver,
        bitmap,
        "Imagen",
        ""
    )

    return Uri.parse(path)
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingCameraXScreen(padding: PaddingValues) {

    val context = LocalContext.current

    var stateUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    LaunchedEffect(key1 = true) {
        permissionState.launchPermissionRequest()
    }

    val cameraController = remember {
        LifecycleCameraController(context)
    }

    val lifecycle = LocalLifecycleOwner.current

    var permission by remember {
        mutableStateOf(false)
    }

    var visibilityComponents by remember {
        mutableStateOf(true)
    }

    if (permission) {
        if (permissionState.status.isGranted) {
            CameraX(
                cameraController = cameraController,
                lifecycle = lifecycle,
                visibilityComponents = visibilityComponents
            )
        } else {
            println("Permiso denegado")
        }
    }

    val singlePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        println(it)
        stateUri = it
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {

        Column {
            if (visibilityComponents) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Nombre")
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = "",
                            onValueChange = {}
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                permission = true
                                visibilityComponents = false
                            }
                        ) {
                            Text(text = "Camara")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                singlePhotoLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                        ) {
                            Text(text = "Galeria")
                        }
                    }
                }
            }

            if (!visibilityComponents) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    FloatingActionButton(
                        onClick = {
                            val executor = ContextCompat.getMainExecutor(context)

                            takePicture(
                                cameraController = cameraController,
                                executor = executor
                            ) {
                                visibilityComponents = true
                                stateUri = it
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (visibilityComponents) {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(context)
                            .data(stateUri)
                            .crossfade(2000)
                            .build(),
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }


        }

    }
}

fun takePicture(
    cameraController: LifecycleCameraController,
    executor: Executor,
    onImageCapture: (Uri?) -> Unit
) {
    var file = File.createTempFile("MyImagen", "jpg")
    val outputDirectory = ImageCapture
        .OutputFileOptions
        .Builder(file)
        .build()
    cameraController.takePicture(
        outputDirectory,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val uri = outputFileResults.savedUri
                onImageCapture(uri)
            }

            override fun onError(exception: ImageCaptureException) {
                println("Error: ${exception.cause}")
            }

        }
    )
}

@Composable
fun CameraX(
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
