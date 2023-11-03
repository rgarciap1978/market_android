package com.mitocode.marketcomposeapp.localdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mitocode.marketcomposeapp.localdb.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ICategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: List<CategoryEntity>)
    @Update
    fun update(category: CategoryEntity)
    @Delete
    fun delete(category: CategoryEntity)
    @Query("SELECT * FROM category ORDER BY name")
    fun select(): Flow<List<CategoryEntity>>
    @Query("SELECT * FROM category WHERE uuid=:uuid")
    fun selectWhereId(uuid: String): CategoryEntity
    @Query("SELECT COUNT(uuid) FROM category")
    fun count(): Int
}