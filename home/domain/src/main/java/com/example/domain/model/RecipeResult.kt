package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeResult(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val thumbnail_url: String,
    val created_at: Long,
    val name: String,
)
