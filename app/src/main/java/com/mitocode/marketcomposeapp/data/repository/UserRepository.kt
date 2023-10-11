package com.mitocode.marketcomposeapp.data.repository

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.model.LoginRequest
import com.mitocode.marketcomposeapp.data.model.UserDTO
import com.mitocode.marketcomposeapp.data.remote.Api
import com.mitocode.marketcomposeapp.domain.repository.IUserPrepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserRepository : IUserPrepository {
    override suspend fun signIn(request: LoginRequest): Flow<Result<UserDTO>> = flow {
        try {
            emit(Result.Loading())
            val response = Api.build().signIn(request)
            if (response.success) {
                emit(Result.Successfull(data = response.data))
            }else{
                emit(Result.Error(message = response.message))
            }
        } catch (ex: HttpException) {
            emit(Result.Error(message = "Encontramos un error en tu solicitud"))
        } catch(ex: IOException){
            emit(Result.Error(message = "No se pudo conectar al servidor"))
        }catch(ex: Exception){
            emit(Result.Error(message = ex.message.toString()))
        }

    }
}