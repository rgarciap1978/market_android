package com.mitocode.marketcomposeapp.repositories

import android.content.SharedPreferences
import com.mitocode.marketcomposeapp.core.Result
import com.mitocode.marketcomposeapp.domain.mappers.DtoToDomain
import com.mitocode.marketcomposeapp.domain.mappers.EntityToDomain
import com.mitocode.marketcomposeapp.domain.mappers.toEntity
import com.mitocode.marketcomposeapp.domain.models.Category
import com.mitocode.marketcomposeapp.localdb.dao.ICategoryDAO
import com.mitocode.marketcomposeapp.localdb.entities.CategoryEntity
import com.mitocode.marketcomposeapp.repositories.interfaces.ICategoryRepository
import com.mitocode.marketcomposeapp.services.IService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    val sharedPreferences: SharedPreferences,
    val service: IService,
    val dao: ICategoryDAO
) : ICategoryRepository {

    override suspend fun getAll(): Result<List<Category>> {
        return try {
            val response = service.getAll()
            if (response.success) {
                Result.Successful(data = response.data.DtoToDomain())
            } else {
                Result.Error(message = response.message)
            }
        } catch (ex: HttpException) {
            Result.Error(message = "Encontramos un error en tu solicitud: ${ex.message()}")
        } catch (ex: IOException) {
            Result.Error(message = "No se pudo conectar al servidor")
        } catch (ex: Exception) {
            Result.Error(message = ex.message.toString())
        }
    }

    override suspend fun sync() {

        val categoriesAPI = getAll()
        categoriesAPI.data?.let {
            val countCategoryAPI = it.size
            val countCategoryLocal = getCount()
            if (countCategoryAPI > countCategoryLocal) {
                save(it.toEntity())
            }
        }
    }

    override suspend fun getCount(): Int = dao.count()

    override suspend fun save(entity: List<CategoryEntity>) {
        dao.insert(entity)
    }

    override val getCategories: Flow<List<Category>> = dao.select().map {
        it.EntityToDomain()
    }
}