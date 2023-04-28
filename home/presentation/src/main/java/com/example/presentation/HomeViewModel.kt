package com.example.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commons.utils.Constants.PAGE_SIZE
import com.example.commons.utils.Status
import com.example.domain.model.RecipeResult
import com.example.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class RecipeState(
    val query: String = "Biryani",
    val count: Int = 0,
    var recipes: MutableList<RecipeResult> = mutableListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
    var page: Int = 0,
    var onObserveClicked: Boolean = false,
    var noResultFound: Boolean = false,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    //private val homeRepositoryUseCase: GetHomeRepositoryUseCase
    private val repository: HomeRepository, private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(RecipeState())
    val uiState: StateFlow<RecipeState> get() = _uiState
    private var recipeListScrollPosition = 0

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            withContext(Dispatchers.Main) {
                _uiState.value.query.let { query ->
                    repository.getAllRecipe(
                        //homeRepositoryUseCase(
                        _uiState.value.page,
                        PAGE_SIZE,
                        query
                    )
                }
                    .collect { resource ->
                        when (resource.status) {

                            Status.SUCCESS -> {
                                val recipes = resource.data
                                //val count = resource.data?.count
                                Log.d("DATA", resource.data.toString())
                                recipes?.let { recipeResults ->

                                    if (recipeResults.isEmpty()) {
                                        _uiState.value = _uiState.value.copy(
                                            isLoading = false,
                                            noResultFound = true,
                                            onObserveClicked = true,
                                            recipes = recipeResults.toMutableList(),
                                        )
                                    } else {
                                        _uiState.value = _uiState.value.copy(
                                            isLoading = false,
                                            recipes = recipeResults.toMutableList(),
                                            onObserveClicked = true,
                                            noResultFound = false
                                        )
                                        Log.d("RESULT", recipeResults.toString())
                                    }
                                }
                            }
                            Status.ERROR -> {
                                _uiState.value =
                                    _uiState.value.copy(isLoading = false, error = resource.message)
                                Log.d("ViewModel_Error", resource.message.toString())
                            }
                            Status.LOADING -> {
                                _uiState.value = _uiState.value.copy(isLoading = true)
                            }
                        }
                    }
            }
        }
    }

    /*private fun nextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if ((recipeListScrollPosition + 1) >= ((_uiState.value.page + 1) * PAGE_SIZE)) {
                _uiState.value = _uiState.value.copy(isLoading = true)
                incrementPage()
                Log.d("PAGE_VALUE", "nextPage: triggered : ${_uiState.value.page}")
                if (_uiState.value.page > 0) {
                    withContext(Dispatchers.Main) {
                        _uiState.value.query.let {
                            homeRepository.getAllRecipe(
                                _uiState.value.page, PAGE_SIZE,
                                it
                            )
                        }.collect { resource ->
                            when (resource.status) {
                                Status.SUCCESS -> {
                                    resource.data?.get(0)?.let { appendRecipes(it.results) }
                                }
                                Status.ERROR -> {
                                    _uiState.value =
                                        _uiState.value.copy(
                                            isLoading = false,
                                            error = resource.message
                                        )
                                }
                                Status.LOADING -> {
                                    _uiState.value =
                                        _uiState.value.copy(isLoading = true)
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/

    private fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    private fun appendRecipes(recipe: List<RecipeResult>) {
        val current = _uiState.value.recipes
        current.addAll(recipe)
        _uiState.value = _uiState.value.copy(recipes = current, isLoading = false)
    }

    private fun incrementPage() {
        _uiState.value.page = _uiState.value.page + 1
    }

    private fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrollPosition = position
    }

    // when new query is searched, clear the list to show shimmer effect
    private fun clearSearch() {
        _uiState.value = _uiState.value.copy(recipes = mutableListOf())
    }
}