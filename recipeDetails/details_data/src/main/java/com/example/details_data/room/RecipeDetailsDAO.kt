package com.example.details_data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.details_domain.model.RecipeDetails

@Dao
interface RecipeDetailsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeDetails(details: RecipeDetails)

    @Query("SELECT * FROM details WHERE id=:id")
    suspend fun getRecipeDetails(id: Int): RecipeDetails
}