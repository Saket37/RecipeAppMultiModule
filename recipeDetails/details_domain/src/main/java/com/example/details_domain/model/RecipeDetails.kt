package com.example.details_domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class RecipeDetails(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    //val instructions : List<Instruction>?,
    val thumbnail_url: String?,
    val name:String?,
    //val sections: List<Section>?
)
