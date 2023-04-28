package com.example.details_presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commons.utils.Status
import com.example.details_domain.model.RecipeDetails
import com.example.details_domain.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class DetailState(
    val details: RecipeDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: DetailsRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> get() = _uiState

    fun observe(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            withContext(Dispatchers.Main) {
                repository.getDetails(id).collect { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            val res = resource.data
                            res?.let { details ->
                                _uiState.value =
                                    _uiState.value.copy(details = details, isLoading = false)
                                Log.d("RESULT", details.toString())
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
}