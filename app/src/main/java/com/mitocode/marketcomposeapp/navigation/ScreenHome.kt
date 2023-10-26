package com.mitocode.marketcomposeapp.navigation

sealed class ScreenHome(val route: String) {

    object Category : ScreenHome("category_screen")
    object Locate : ScreenHome("locate_screen")
    object Setting : ScreenHome("setting_screen")
    object Product : ScreenHome("product_screen/?uuid={uuid}"){
        fun createRoute(uuid:String) = "product_screen/?uuid=$uuid"
    }
}
