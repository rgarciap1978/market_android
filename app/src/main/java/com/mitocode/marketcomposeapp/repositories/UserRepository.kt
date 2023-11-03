package com.mitocode.marketcomposeapp.repositories

import android.content.SharedPreferences
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import com.mitocode.marketcomposeapp.domain.mappers.toUser
import com.mitocode.marketcomposeapp.domain.models.User
import com.mitocode.marketcomposeapp.repositories.interfaces.IUserRepository
import com.mitocode.marketcomposeapp.services.IService
import com.mitocode.marketcomposeapp.services.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepository @Inject constructor(
    val sharedPreferences: SharedPreferences,
    val service: IService
) : IUserRepository {
    override suspend fun signIn(request: LoginRequest): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading())
            val response = service.signIn(request)

            if (response.success) {
                TokenManager.setToken(response.token)

                sharedPreferences
                    .edit()
                    .putString("Id", response.data?.Id)
                    .putString("Firstname", response.data?.Firstname)
                    .putString("Lastname", response.data?.Lastname)
                    .putString("Email", response.data?.Email)
                    .putString("Phone", response.data?.Phone)
                    .apply()

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