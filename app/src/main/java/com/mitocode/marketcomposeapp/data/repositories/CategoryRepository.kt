package com.mitocode.marketcomposeapp.data.repositories

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.dtos.CategoryDTO
import com.mitocode.marketcomposeapp.data.repositories.interfaces.ICategoryRepository
import com.mitocode.marketcomposeapp.services.CategoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CategoryRepository : ICategoryRepository {
    override suspend fun GetCategory(): Flow<Result<CategoryDTO>> = flow {
        try {
            emit(Result.Loading())
            val response = CategoryService.build().getAll()

        } catch (ex: HttpException) {
            emit(Result.Error(message = "Encontramos un error en tu solicitud"))
        } catch (ex: IOException) {
            emit(Result.Error(message = "No se pudo conectar al servidor"))
        } catch (ex: Exception) {
            emit(Result.Error(message = ex.message.toString()))
        }
    }
}