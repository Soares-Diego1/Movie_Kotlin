package org.example.projectt.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.example.projectt.data.mapper.toModel
import org.example.projectt.domain.model.MovieSection
import org.example.projectt.data.network.KtorClient
import org.example.projectt.domain.model.CastMember
import org.example.projectt.domain.model.ImageSize
import org.example.projectt.domain.model.Movie


class MoviesRepository (
    private val KtorClient: KtorClient,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,


){
    suspend fun getMovieSections(): List<MovieSection> {
        return withContext(ioDispatcher) {

            val popularMoviesDeferred = async { KtorClient.getMovies(category = "popular") }
            val topRatedDeferred = async { KtorClient.getMovies(category = "top_rated") }
            val upcomingDeferred = async { KtorClient.getMovies(category = "upcoming") }

            val popularMovies = popularMoviesDeferred.await()
            val topRatedMovies = topRatedDeferred.await()
            val upcomingMovies = upcomingDeferred.await()

            listOf(
                MovieSection(
                    sectionType = MovieSection.SectionType.POPULAR,
                    movies = popularMovies.results.map {
                        it.toModel()
                    }
                ),
                MovieSection(
                    sectionType = MovieSection.SectionType.TOP_RATED,
                    movies = topRatedMovies.results.map {
                        it.toModel()
                    }
                ),
                MovieSection(
                    sectionType = MovieSection.SectionType.UPCOMING,
                    movies = upcomingMovies.results.map {
                        it.toModel()
                    }

                ),
            )
        }
    }

    suspend fun getMovieDetail(movieId: Int): Result<Movie>{
        return withContext(ioDispatcher){
            runCatching {
                val movieDetailDeferred = async { KtorClient.getMoviesDetail(movieId) }
                val creditsDeferred = async { KtorClient.getCredits(movieId) }
               val videosDeferred = async { KtorClient.getVideos(movieId) }

                val movieDetailResponse = movieDetailDeferred.await()
                val creditsResponse =creditsDeferred.await()
                val videosResponse =  videosDeferred.await()

                val movieTrailerYoutubeKey = videosResponse.results.firstOrNull {  videoResponse ->
                    videoResponse.site == "YouTube"
                }?.key


                movieDetailResponse.toModel(
                    creditsResponse.cast,
                    movieTrailerYoutubeKey=movieTrailerYoutubeKey,
                 imageSize = ImageSize.X_LARGE
                )
            }
        }
    }
    suspend fun getCast(movieId: Int): Result<List<CastMember>> {
        return withContext(ioDispatcher) {
            runCatching {
                val creditsResponse = KtorClient.getCredits(movieId)

                creditsResponse.cast.map { it.toModel() }
            }
        }
    }


}