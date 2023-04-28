package com.example.details_domain.repository

import com.example.commons.utils.Resource
import com.example.details_domain.model.RecipeDetails
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    suspend fun getDetails(id:Int): Flow<Resource<RecipeDetails>>
}