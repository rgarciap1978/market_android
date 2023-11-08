package com.mitocode.marketcomposeapp.services

import com.mitocode.marketcomposeapp.data.dto.CategoryDTO
import com.mitocode.marketcomposeapp.data.dto.ProductDTO
import com.mitocode.marketcomposeapp.data.dto.UserDTO
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import com.mitocode.marketcomposeapp.data.responses.GenericListResponse
import com.mitocode.marketcomposeapp.data.responses.GenericResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IService {
    @POST("api/usuarios/login")
    suspend fun signIn(@Body request: LoginRequest): GenericResponse<UserDTO>

    @POST("api/usuarios/renueva-token")
    suspend fun refreshToken(): GenericResponse<UserDTO>

    @GET("api/categorias")
    suspend fun getAll(): GenericListResponse<CategoryDTO>

    @GET("api/categorias/{uuid}/productos")
    suspend fun findById(@Path("uuid") uuid: String): GenericListResponse<ProductDTO>
}