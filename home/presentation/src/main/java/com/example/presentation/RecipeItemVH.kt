package com.example.presentation

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.commons.utils.DateHelper.unixSecondsToText
import com.example.domain.model.RecipeResult
import com.example.presentation.databinding.ItemRecipeBinding
import com.squareup.picasso.Picasso

class RecipeItemVH( val binding: ItemRecipeBinding) : ViewHolder(binding.root) {
    fun bind(recipe: RecipeResult) {
        binding.apply {
            title.text = recipe.name
            timeTV.text = recipe.created_at.unixSecondsToText("dd-MM-yyyy")
            try {
                Picasso.get().load(recipe.thumbnail_url).into(recipeIV)
            } catch (e: ArrayIndexOutOfBoundsException) {
                e.printStackTrace()
                Toast.makeText(
                    binding.root.context, "Error retrieving Image", Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}