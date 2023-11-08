package com.mitocode.marketcomposeapp.repositories.interfaces

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.requests.LoginRequest
import com.mitocode.marketcomposeapp.domain.models.User
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    suspend fun signIn(request: LoginRequest): Flow<Result<User>>

    suspend fun refreshToken(): Boolean
}