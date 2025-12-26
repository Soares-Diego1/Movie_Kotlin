package org.example.projectt.ui.cast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.projectt.data.repository.MoviesRepository // Verifique se este é o caminho correto
import org.example.projectt.domain.model.CastMember

class CastMembersViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CastMembersState>(CastMembersState.Loading)
    val uiState = _uiState.asStateFlow()

    init {

        val movieId: Int? = savedStateHandle["movieId"]

        if (movieId != null) {
            loadCast(movieId)
        } else {
            _uiState.update { CastMembersState.Error("Movie ID not found") }
        }
    }

    private fun loadCast(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { CastMembersState.Loading }


            val result = moviesRepository.getCast(movieId)

            result.fold(
                onSuccess = { castList ->
                    _uiState.update { CastMembersState.Success(castList) }
                },
                onFailure = { error ->
                    _uiState.update { CastMembersState.Error(error.message ?: "Unknown error") }
                }
            )
        }
    }

    sealed interface CastMembersState {
        data object Loading : CastMembersState
        data class Success(val castMembers: List<CastMember>) : CastMembersState
        data class Error(val message: String) : CastMembersState
    }
}