package com.example.data.repository

import com.example.commons.utils.Resource
import com.example.data.mapper.map
import com.example.data.network.ApiService
import com.example.data.room.RecipeDAO
import com.example.domain.model.RecipeResult
import com.example.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val recipeDAO: RecipeDAO
) : HomeRepository {
    override suspend fun getAllRecipe(
        from: Int,
        size: Int,
        recipeName: String
    ): Flow<Resource<List<RecipeResult>>> = flow {
        try {
            val getAll = apiService.getRecipe(from, size, recipeName).results.map {
                map(it)
            }
            recipeDAO.insertList(getAll)
            emit(Resource.success(recipeDAO.getRecipeList()))
        } catch (e: Exception) {
            emit(Resource.success(recipeDAO.getRecipeList()))
        }
    }
}