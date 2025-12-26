package org.example.projectt.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Star
import org.example.projectt.domain.model.MovieSection
import org.example.projectt.domain.model.movie1
import org.example.projectt.ui.components.CastMemberItem
import org.example.projectt.ui.components.MovieGenreChip
import org.example.projectt.ui.components.MovieInfoItem
import org.example.projectt.ui.components.MoviePoster
import org.example.projectt.ui.moviedetail.MovieDetailScreen
import org.example.projectt.ui.moviedetail.MovieDetailViewModel
import org.example.projectt.ui.movies.MoviesListScreen
import org.example.projectt.ui.movies.MoviesListViewModel
import org.example.projectt.ui.theme.MoviesAppTheme


@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview() {

    MoviePoster(
        movie = movie1,
                onMoviePosterClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun MovieListScreenPreview() {
    MoviesAppTheme{
        MoviesListScreen(
            moviesListState = MoviesListViewModel.MoviesListState.Sucess(
                movieSections = listOf(
                    MovieSection(
                        sectionType = MovieSection.SectionType.POPULAR,
                        movies = listOf(
                            movie1,
                        ),

                    )
                )

            ),
            onMovieClick = {},

        )
    }


}

@Preview(showBackground = true)
@Composable
private fun MovieDetailScreenPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            movieDetailState = MovieDetailViewModel.MovieDetailState.Sucess(movie1),
            onNavigationIconClick = {},
            onViewAllCastClick = {}
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun MovieInfoItemPreview() {
    MoviesAppTheme {
       MovieInfoItem(
            icon = FontAwesomeIcons.Solid.Star,
           text = "text"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieGenreChipPreview() {
    MoviesAppTheme {
        MovieGenreChip(
           genre = "Action"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CastMemberItemPreview() {
    MoviesAppTheme {
        CastMemberItem(
            profilePictureUrl = "url",
         name = "Will",
            character = "john"

        )
    }
}