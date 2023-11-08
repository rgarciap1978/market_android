package com.mitocode.marketcomposeapp.presentation.locate

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.mitocode.marketcomposeapp.presentation.component.GoogleMapComponent

@Composable
fun LocateScreen(padding: PaddingValues) {

    val uiSettings = remember {
        MapUiSettings(myLocationButtonEnabled = true)
    }

    var myLocationEnabled by remember {
        mutableStateOf(false)
    }

    val myPosition = LatLng(-33.799337, 151.179329)

    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myPosition, 16f)
    }

    val coarsePermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        when {
            it -> {
                myLocationEnabled = true
            }

            else -> {
                println("Permiso denegado")
            }
        }
    }

    LaunchedEffect(key1 = true) {
        coarsePermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        GoogleMapComponent(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings,
            cameraPosition = cameraPosition,
            myPosition = myPosition,
            isMyLocationEnabled = myLocationEnabled
        )
    }
}
