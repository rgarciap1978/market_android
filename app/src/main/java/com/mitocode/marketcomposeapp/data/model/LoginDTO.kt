package com.mitocode.marketcomposeapp.data.model

data class LoginDTO(
    val success: Boolean,
    val message: String,
    val data: UserDTO?,
    val token: String
)