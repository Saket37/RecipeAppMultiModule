package com.example.domain.use_case

import com.example.commons.utils.Resource
import com.example.domain.model.RecipeResult
import com.example.domain.model.Result
import com.example.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHomeRepositoryUseCase(private val homeRepository: HomeRepository) {
    operator fun invoke(
        from: Int,
        size: Int,
        recipeName: String
    ): Flow<Resource<List<RecipeResult>>> = flow {
        emit(Resource.loading(null))
        try {
            Resource.success(data = homeRepository.getAllRecipe(from, size, recipeName))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }
}