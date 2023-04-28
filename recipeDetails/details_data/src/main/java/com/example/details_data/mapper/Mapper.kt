package com.example.details_data.mapper

import com.example.details_data.model.*
import com.example.details_domain.model.RecipeDetails

fun map(dto: RecipeDetailsResponse): RecipeDetails {
    return RecipeDetails(
        id = dto.id,
        //instructions = dto.instructions?.map { mapInstructions(it) },
        thumbnail_url = dto.thumbnail_url,
        name = dto.name,
        //sections = dto.sections?.map { mapSection(it) }
    )
}

fun mapInstructions(dto: Instruction): com.example.details_domain.model.Instruction {
    return com.example.details_domain.model.Instruction(
        appliance = dto.appliance,
        display_text = dto.display_text,
        end_time = dto.end_time,
        id = dto.id,
        position = dto.position,
        start_time = dto.start_time,
        temperature = dto.temperature
    )
}

fun mapSection(dto: Section): com.example.details_domain.model.Section {
    return com.example.details_domain.model.Section(
        name = dto.name,
        position = dto.position,
        components = dto.components?.map { mapComponent(it) }
    )
}

fun mapComponent(dto: Component): com.example.details_domain.model.Component {
    return com.example.details_domain.model.Component(
        extra_comment = dto.extra_comment,
        id = dto.id,
        ingredient = dto.ingredient?.let { mapIngredient(it) },
        measurements = dto.measurements?.map { mapMeasurement(it) },
        position = dto.position,
        raw_text = dto.raw_text
    )
}

fun mapIngredient(dto: Ingredient): com.example.details_domain.model.Ingredient {
    return com.example.details_domain.model.Ingredient(
        created_at = dto.created_at,
        display_plural = dto.display_plural,
        id = dto.id,
        name = dto.name,
        updated_at = dto.updated_at
    )
}

fun mapMeasurement(dto: Measurement): com.example.details_domain.model.Measurement {
    return com.example.details_domain.model.Measurement(id = dto.id, quantity = dto.quantity)
}