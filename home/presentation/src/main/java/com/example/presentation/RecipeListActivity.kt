package com.example.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.commons.utils.DeeplinkProcessor
import com.example.details_presentation.RecipeDetailsActivity
import com.example.presentation.databinding.ActivityRecipeListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListActivity : AppCompatActivity() {
    private var _binding: ActivityRecipeListBinding? = null
    private val binding: ActivityRecipeListBinding get() = _binding!!

    @Inject
    lateinit var recipeItemAdapter: RecipeItemAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
        observe()
        setupRV()
        listeners()
    }

    private fun listeners() {
        recipeItemAdapter.onClick = fun(id: Int) {
            Log.d("ON_CLICK", id.toString())

            val intent =
             Intent(Intent.ACTION_VIEW, Uri.parse("recipe://multi.module.app/details")).apply {
                 putExtra(DeeplinkProcessor.EXTRA_KEY, id)
             }
            startActivity(intent)
        }
    }

    private fun setupRV() {
        binding.recipeRV.apply {
            layoutManager =
                GridLayoutManager(this@RecipeListActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = recipeItemAdapter
            val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
            addItemDecoration(GridSpacingItemDecoration(spacing))
        }

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
                    Toast.makeText(this@RecipeListActivity, error, Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressBar.visibility = View.GONE
                    Log.d("ADAPTER_DATA", uiState.recipes.toString())
                    recipeItemAdapter.submitList(uiState.recipes)
                }
            }
        }
    }
}