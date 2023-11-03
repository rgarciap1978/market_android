package com.mitocode.marketcomposeapp.localdb.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    val uuid: String,
    val name: String,
    val cover: String,
    val status: String
)
