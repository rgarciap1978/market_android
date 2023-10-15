package com.mitocode.marketcomposeapp.services

object TokenManager {
    private var authToken: String = ""

    fun setToken(token: String) {
        authToken = token
    }

    fun getToken(): String {
        return authToken
    }
}