package com.example.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.RecipeResult

@Dao
interface RecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<RecipeResult>)

    @Query("SELECT * FROM recipe")
    suspend fun getRecipeList(): List<RecipeResult>
}