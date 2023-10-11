package com.mitocode.marketcomposeapp.data.model

data class LoginRequest(
    val email: String,
    val password: String,
    val firebaseToken: String = ""
)