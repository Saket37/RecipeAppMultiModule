package com.example.details_presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.commons.utils.DeeplinkProcessor
import com.example.details_presentation.databinding.ActivityRecipeDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {
    private var _binding: ActivityRecipeDetailsBinding? = null
    private val binding: ActivityRecipeDetailsBinding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(DeeplinkProcessor.EXTRA_KEY)
        Log.d("INTENT", id.toString())
        id?.let { viewModel.observe(it.toInt()) }
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { uiState ->
                if (uiState.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else if (uiState.error?.isNotBlank() == true) {
                    binding.progressBar.visibility = View.GONE
                    val error = uiState.error.toString()
                    Log.e("Error", error)
                    Toast.makeText(this@RecipeDetailsActivity, error, Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.recipeName.text = uiState.details?.name
                    try {
                        Picasso.get().load(uiState.details?.thumbnail_url).into(binding.recipeIV)
                    } catch (e: ArrayIndexOutOfBoundsException) {
                        e.printStackTrace()
                        Toast.makeText(
                            binding.root.context, "Error retrieving Image", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}