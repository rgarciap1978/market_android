package com.mitocode.marketcomposeapp.data.repositories.interfaces

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.dtos.UserDTO
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    suspend fun signIn(request: LoginRequest) : Flow<Result<UserDTO>>
}