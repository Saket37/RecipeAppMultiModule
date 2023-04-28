package com.example.domain.repository

import com.example.commons.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getAllRecipe(
        from: Int,
        size: Int,
        recipeName: String
    ): Flow<Resource<List<com.example.domain.model.RecipeResult>>>
}