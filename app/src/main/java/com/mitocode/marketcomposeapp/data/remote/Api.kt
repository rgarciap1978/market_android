package com.mitocode.marketcomposeapp.data.remote

import com.mitocode.marketcomposeapp.data.model.CategoryDTO
import com.mitocode.marketcomposeapp.data.model.LoginDTO
import com.mitocode.marketcomposeapp.data.model.LoginRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Singlenton
object Api {

    val url = "http://35.169.242.154:3000/"

    val builder = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())

    interface Methods{

        @POST("api/usuarios/login")
        suspend fun signIn(@Body request: LoginRequest) : LoginDTO

        @GET("api/categorias")
        suspend fun getCategories() : CategoryDTO
    }

    fun build() : Methods {
        return builder.build().create(Methods::class.java)
    }
}