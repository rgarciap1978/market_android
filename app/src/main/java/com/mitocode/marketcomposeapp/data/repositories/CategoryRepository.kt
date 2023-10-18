package com.mitocode.marketcomposeapp.data.repositories

import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.data.repositories.interfaces.ICategoryRepository
import com.mitocode.marketcomposeapp.domain.mappers.toListCategory
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.services.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CategoryRepository : ICategoryRepository {

    override suspend fun populateCategories(): Flow<Result<List<Category>>> = flow {
        try {
            emit(Result.Loading())
            val response = Api.build().getAll()
            if(response.success) {
                emit(Result.Successful(data = response.data.toListCategory()))
            }else{
                emit(Result.Error(message = response.message))
            }
        } catch (ex: HttpException) {
            emit(Result.Error(message = "Encontramos un error en tu solicitud"))
        } catch (ex: IOException) {
            emit(Result.Error(message = "No se pudo conectar al servidor"))
        } catch (ex: Exception) {
            emit(Result.Error(message = ex.message.toString()))
        }
    }
}