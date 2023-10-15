package com.mitocode.marketcomposeapp.data.repositories.interfaces

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    suspend fun GetCategory() : Flow<Result<CategoryDTO>>
}