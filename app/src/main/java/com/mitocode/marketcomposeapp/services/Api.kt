package com.mitocode.marketcomposeapp.services

import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO
import com.mitocode.marketcomposeapp.data.dtos.UserDTO
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import com.mitocode.marketcomposeapp.data.responses.GenericListResponse
import com.mitocode.marketcomposeapp.data.responses.GenericResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object Api {
    val url = "http://35.169.242.154:3000/"

    var client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", "Bearer ${TokenManager.getToken()}")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        .build()

    val builder = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)

    interface Methods{

        @POST("api/usuarios/login")
        suspend fun signIn(@Body request: LoginRequest) : GenericResponse<UserDTO>

        @GET("api/categorias")
        suspend fun getAll() : GenericListResponse<CategoryDTO>

        @GET("api/categorias/{id}/productos")
        suspend fun findById() : GenericResponse<CategoryDTO>
    }

    fun build() : Methods {
        return builder.build().create(Methods::class.java)
    }
}