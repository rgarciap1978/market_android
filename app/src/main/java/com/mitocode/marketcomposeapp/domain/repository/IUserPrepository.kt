package com.mitocode.marketcomposeapp.domain.repository

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.model.LoginRequest
import com.mitocode.marketcomposeapp.data.model.UserDTO
import kotlinx.coroutines.flow.Flow


interface IUserPrepository {
    suspend fun signIn(request: LoginRequest) : Flow<Result<UserDTO>>
}