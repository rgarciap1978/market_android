package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapComponent(
    modifier: Modifier = Modifier
) {
    val myPosition = LatLng(-33.799337, 151.179329)

    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(myPosition, 16f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPosition,
        properties = MapProperties(
            mapType = MapType.HYBRID
        )
    ) {
        Marker(
            position = myPosition,
            title = "Chatswood"
        )
    }
}