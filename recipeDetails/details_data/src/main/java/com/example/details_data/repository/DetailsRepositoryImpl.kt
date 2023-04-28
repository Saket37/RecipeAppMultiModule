package com.example.details_data.repository

import com.example.commons.utils.Resource
import com.example.details_data.mapper.map
import com.example.details_data.network.ApiService
import com.example.details_data.room.RecipeDetailsDAO
import com.example.details_domain.model.RecipeDetails
import com.example.details_domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailsRepositoryImpl(
    private val apiService: ApiService,
    private val recipeDetailsDAO: RecipeDetailsDAO
) : DetailsRepository {
    override suspend fun getDetails(id: Int): Flow<Resource<RecipeDetails>> = flow {
        try {
            val res = apiService.getRecipe(id).run {
                map(this)
            }
            recipeDetailsDAO.insertRecipeDetails(res)
            emit(Resource.success(recipeDetailsDAO.getRecipeDetails(id)))
        } catch (e: Exception) {
            emit(Resource.success(recipeDetailsDAO.getRecipeDetails(id)))
        }

    }
}