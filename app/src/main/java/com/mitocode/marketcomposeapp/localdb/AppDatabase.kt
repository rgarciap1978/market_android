package com.mitocode.marketcomposeapp.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mitocode.marketcomposeapp.localdb.dao.ICategoryDAO
import com.mitocode.marketcomposeapp.localdb.entities.CategoryEntity

@Database(
    entities = [
        CategoryEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDAO(): ICategoryDAO
}