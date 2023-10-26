package com.mitocode.marketcomposeapp.data.repositories.interfaces

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    suspend fun getAll() : Flow<Result<List<Category>>>
}