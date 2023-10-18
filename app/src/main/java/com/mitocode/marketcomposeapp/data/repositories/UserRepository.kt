package com.mitocode.marketcomposeapp.data.repositories

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.repositories.interfaces.IUserRepository
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import com.mitocode.marketcomposeapp.domain.mappers.toUser
import com.mitocode.marketcomposeapp.domain.models.User
import com.mitocode.marketcomposeapp.services.Api
import com.mitocode.marketcomposeapp.services.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserRepository : IUserRepository {
    override suspend fun signIn(request: LoginRequest): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading())
            val response = Api.build().signIn(request)
            TokenManager.setToken(response.token)

            if (response.success) {
                emit(Result.Successful(data = response.data?.toUser()))
            } else {
                emit(Result.Error(message = response.message))
            }
        } catch (ex: HttpException) {
            emit(Result.Error(message = "Encontramos un error en tu solicitud"))
        } catch (ex: IOException) {
            emit(Result.Error(message = "No se pudo conectar al servidor"))
        } catch (ex: Exception) {
            emit(Result.Error(message = ex.message.toString()))
            println(ex.message.toString())
        }
    }
}