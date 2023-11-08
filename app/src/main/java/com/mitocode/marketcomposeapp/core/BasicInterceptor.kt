package com.mitocode.marketcomposeapp.core

import com.mitocode.marketcomposeapp.services.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class BasicInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = TokenManager.getToken()

        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}