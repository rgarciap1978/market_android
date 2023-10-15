package com.mitocode.marketcomposeapp.services

import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object CategoryService {
    val url = "http://35.169.242.154:3000/"

    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", "Bearer ${TokenManager.getToken()}")
                .method(original.method(),original.body())
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
        @GET("api/categorias")
        suspend fun getAll() : CategoryResponse
    }

    fun build() : Methods {
        return builder.build().create(Methods::class.java)
    }

}

data class CategoryResponse (
    val success: Boolean,
    val message: String,
    val data: List<CategoryDTO>
)