package com.example.data.mapper

import com.example.data.model.RecipeResultResponse
import com.example.domain.model.RecipeResult
import com.example.domain.model.Result

/*
fun RecipeResultResponse.toDomainResponse(): com.example.domain.model.Result {

    return Result(count = this.count, results = this.results.map { it.toResp() })
}

fun com.example.data.model.RecipeResult.toResp(): RecipeResult {
    return RecipeResult(
        thumbnail_url = this.thumbnail_url,
        created_at = this.created_at,
        name = this.name
    )
}*/


    fun map(dto: com.example.data.model.RecipeResult): RecipeResult {
        return RecipeResult(
            thumbnail_url = dto.thumbnail_url,
            created_at = dto.created_at,
            name = dto.name,
            id = dto.id
        )
    }

