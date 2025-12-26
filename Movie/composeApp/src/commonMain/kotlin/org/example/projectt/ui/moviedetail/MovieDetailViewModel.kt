package org.example.projectt.ui.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.projectt.data.repository.MoviesRepository
import org.example.projectt.domain.model.Movie
import org.example.projectt.navigation.AppRoutes
import org.koin.core.logger.MESSAGE

class MovieDetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository,

): ViewModel(){
    private val movieDetailRoute = savedStateHandle.toRoute<AppRoutes.MovieDetail>()

    private val _movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.loading)
    val movieDetailState = _movieDetailState.asStateFlow()

    init {
    getMovieDetail()
    }

    private fun getMovieDetail(){
        viewModelScope.launch {
            moviesRepository.getMovieDetail(movieDetailRoute.id).fold(
                onSuccess = { movie ->
                    _movieDetailState.update {
                        MovieDetailState.Sucess(movie)
                    }

                },
                onFailure = {error->
                    _movieDetailState.update {
                        MovieDetailState.Error(error.message?:  "Unknow error")
                    }
                }
            )
        }
    }

    sealed interface MovieDetailState {
        data object loading : MovieDetailState
        data class Sucess(val movie: Movie): MovieDetailState
        data class Error(val message: String): MovieDetailState
    }
}