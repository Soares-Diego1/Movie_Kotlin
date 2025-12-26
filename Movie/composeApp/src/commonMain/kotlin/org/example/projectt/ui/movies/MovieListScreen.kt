package org.example.projectt.ui.movies


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.projectt.data.network.KtorClient
import org.example.projectt.domain.model.Movie
import org.example.projectt.domain.model.movie1
import org.example.projectt.ui.components.MoviesSection
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.projectt.data.repository.MoviesRepository
import org.example.projectt.domain.model.MovieSection
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MoviesListRoute(
    viewModel: MoviesListViewModel = koinViewModel(),
    navigateToMovieDetail: (movieId: Int) -> Unit,
){

       val moviesListState by viewModel.moviesListState.collectAsStateWithLifecycle()

MoviesListScreen(
    moviesListState=moviesListState,
    onMovieClick = navigateToMovieDetail,
)
}

@Composable
fun MoviesListScreen(
moviesListState: MoviesListViewModel.MoviesListState,
onMovieClick: (movieId: Int) -> Unit,
){
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when(moviesListState){

                MoviesListViewModel.MoviesListState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
                }
                is MoviesListViewModel.MoviesListState.Sucess ->{
                    LazyColumn(
                        modifier = Modifier,
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        items(moviesListState.movieSections){ movieSection ->
                           val title = when(movieSection.sectionType){
                               MovieSection.SectionType.POPULAR -> "Popular Movies"
                               MovieSection.SectionType.TOP_RATED ->"Top Rated Movies"
                               MovieSection.SectionType.UPCOMING -> "Upcoming Movies"
                           }
                            MoviesSection(
                               title = title,
                               movies =movieSection.movies,
                                onMoviePosterClick=onMovieClick,
                           )

                        }



                    }
                }
                is MoviesListViewModel.MoviesListState.Error -> {
                    Text(
                        text = moviesListState.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                        .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

}
