package org.example.projectt.ui.movies
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.projectt.data.repository.MoviesRepository
import org.example.projectt.domain.model.MovieSection

class MoviesListViewModel (
    private val moviesRepository: MoviesRepository
): ViewModel(){
    private val _moviesListState = MutableStateFlow<MoviesListState>(MoviesListState.Loading)
    val moviesListState =_moviesListState.asStateFlow()

    init {
        getMovieSections()
    }

    private fun getMovieSections(){
        viewModelScope.launch {
            try {
                val movieSections = moviesRepository.getMovieSections()
                _moviesListState.update {
                    MoviesListState.Sucess(movieSections)
                }
            }catch (e: Exception){
                _moviesListState.update {
                    MoviesListState.Error(e.message?:"Erro desconhecido")
                }
            }

        }
    }

    sealed interface MoviesListState{
        data object Loading : MoviesListState
        data class Sucess(val movieSections : List<MovieSection>): MoviesListState
        data class Error(val message:String): MoviesListState
    }

}