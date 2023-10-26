package com.mitocode.marketcomposeapp.data.repositories.interfaces

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun findById(id: String): Flow<Result<List<Product>>>
}