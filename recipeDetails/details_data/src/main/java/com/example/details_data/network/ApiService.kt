package com.example.details_data.network

import com.example.details_data.model.RecipeDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/recipes/get-more-info")
    suspend fun getRecipe(
        @Query("id") id: Int,
    ): RecipeDetailsResponse

}