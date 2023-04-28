package com.example.data.network

import com.example.data.model.RecipeResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
        @GET("/recipes/list")
        suspend fun getRecipe(
            @Query("from") from: Int,
            @Query("size") size: Int,
            @Query("q") q: String
        ): RecipeResultResponse

}