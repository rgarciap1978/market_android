package com.mitocode.marketcomposeapp.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun GoogleMapComponent(
    modifier: Modifier = Modifier,
    uiSettings: MapUiSettings,
    cameraPosition: CameraPositionState,
    myPosition: LatLng,
    isMyLocationEnabled: Boolean
) {
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPosition,
        properties = MapProperties(
            mapType = MapType.HYBRID,
            isMyLocationEnabled = isMyLocationEnabled
        ),
        uiSettings = uiSettings
    ) {
        Marker(
            position = myPosition,
            title = "Chatswood"
        )
    }
}