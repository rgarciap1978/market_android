package com.mitocode.marketcomposeapp.data.requests

data class LoginRequest(
    val email: String,
    val password: String,
    val firebaseToken: String = ""
)