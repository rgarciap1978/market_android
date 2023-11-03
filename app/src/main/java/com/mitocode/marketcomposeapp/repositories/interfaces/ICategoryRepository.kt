package com.mitocode.marketcomposeapp.repositories.interfaces

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.localdb.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    suspend fun getAll() : Result<List<Category>>
    suspend fun sync()
    suspend fun getCount():Int
    suspend fun save(entity: List<CategoryEntity>)

    val getCategories:Flow<List<Category>>
}