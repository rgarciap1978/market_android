package com.mitocode.marketcomposeapp.services

import com.mitocode.marketcomposeapp.data.dtos.UserDTO
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import com.mitocode.marketcomposeapp.data.responses.GenericResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Singlenton
object LoginService {

    val url = "http://35.169.242.154:3000/"

    val builder = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())

    interface Methods{

        @POST("api/usuarios/login")
        suspend fun signIn(@Body request: LoginRequest) : GenericResponse<UserDTO>
    }

    fun build() : Methods {
        return builder.build().create(Methods::class.java)
    }
}