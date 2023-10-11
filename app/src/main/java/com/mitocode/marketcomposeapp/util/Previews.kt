package com.mitocode.marketcomposeapp.util


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Preview", showSystemUi = true, device = Devices.PHONE)
annotation class DefaultPreview

@Preview(name = "Preview Component", showBackground = true)
annotation class DefaultComponentPreview

@Preview(name = "Preview Component", showBackground = true, uiMode=UI_MODE_NIGHT_YES)
@Preview(name = "Preview Component", showBackground = true)
annotation class DefaultComponentLightOrDarkPreview
