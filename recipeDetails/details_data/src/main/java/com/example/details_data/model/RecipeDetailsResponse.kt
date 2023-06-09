package com.example.details_data.model

import com.squareup.moshi.Json

data class RecipeDetailsResponse(
    @field:Json(name = "thumbnail_url") val thumbnail_url: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "id") val id: Int,

   /* @field:Json(name = "num_servings") val num_servings: Int?,
    @field:Json(name = "promotion") val promotion: String?,
    @field:Json(name = "seo_title") val seo_title: String?,
    @field:Json(name = "total_time_minutes") val total_time_minutes: String?,
    @field:Json(name = "compilations") val compilations: List<String>?,
   // @field:Json(name = "user_ratings") val user_ratings: UserRatings?,
    @field:Json(name = "credits") val credits: List<Credit>?,
    @field:Json(name = "keywords") val keywordswords: String?,
    @field:Json(name = "topics") val topics: List<Topic>?,
    @field:Json(name = "show") val show: Show?,
    @field:Json(name = "thumbnail_alt_text") val thumbnail_alt_text: String?,
    @field:Json(name = "yields") val yields: String?,
    @field:Json(name = "canonical_id") val canonical_id: String?,
    @field:Json(name = "draft_status") val draft_status: String?,
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "instructions") val instructions: List<Instruction>?,
    @field:Json(name = "cook_time_minutes") val cook_time_minutes: String?,
    @field:Json(name = "inspired_by_url") val inspired_by_url: String?,
    @field:Json(name = "prep_time_minutes") val prep_time_minutes: String?,
    @field:Json(name = "servings_noun_plural") val servings_noun_plural: String?,
    @field:Json(name = "show_id") val show_id: Int?,
    @field:Json(name = "updated_at") val updated_at: Int?,
    @field:Json(name = "sections") val sections: List<Section>?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "nutrition") val nutrition: Nutrition?,
    @field:Json(name = "tags") val tags: List<Tag>?,
    @field:Json(name = "is_shoppable") val is_shoppable: Boolean?,
    @field:Json(name = "brand") val brand: String?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "buzz_id") val buzz_id: String?,
    @field:Json(name = "tips_and_ratings_enabled") val tips_and_ratings_enabled: Boolean?,
    @field:Json(name = "original_video_url") val original_video_url: String?,
    @field:Json(name = "beauty_url") val beauty_url: String?,
    @field:Json(name = "servings_noun_singular") val servings_noun_singular: String?,
    @field:Json(name = "slug") val slug: String?,
    @field:Json(name = "video_ad_content") val video_ad_content: String?,
    @field:Json(name = "price") val price: Price?,
    @field:Json(name = "seo_path") val seo_path: String?,
    @field:Json(name = "aspect_ratio") val aspect_ratio: String?,
    @field:Json(name = "brand_id") val brand_id: String?,
    @field:Json(name = "created_at") val created_at: Int?,
    @field:Json(name = "is_one_top") val is_one_top: Boolean?,
    @field:Json(name = "video_id") val video_id: String?,
    @field:Json(name = "video_url") val video_url: String?,
    @field:Json(name = "approved_at") val approved_at: Int?,
    @field:Json(name = "facebook_posts") val facebook_posts: List<String>?,
    @field:Json(name = "nutrition_visibility") val nutrition_visibility: String?*/
)