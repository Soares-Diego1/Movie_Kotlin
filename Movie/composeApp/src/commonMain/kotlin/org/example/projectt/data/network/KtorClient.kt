package org.example.projectt.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.projectt.data.network.model.CreditsListResponse
import org.example.projectt.data.network.model.MoviesListResponse
import org.example.projectt.data.network.model.MoviesResponse
import org.example.projectt.data.network.model.VideosListResponse

private const val BASE_URL ="https://api.themoviedb.org"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"

class KtorClient {

    private val client= HttpClient{
        install(ContentNegotiation){
            json(
                Json {
                    prettyPrint=true
                    isLenient=true
                    ignoreUnknownKeys=true
                }
            )
        }
        install(Auth){
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZjQ0YTg3ZmIzZTM0MWJhNzIzN2JhMWE4MzI2NzY3NyIsIm5iZiI6MTc1ODE5MTE5OC42MzEsInN1YiI6IjY4Y2JkZTVlZjRkYTY5MmNjMWJkMGIzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XyLBdoVw8b90R4yH4AVLz4bHqL9MfrujtTKH53MY0B4",
                        refreshToken = "",
                    )
                }
            }
        }
        install(Logging){
            logger = Logger.SIMPLE
            level= LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }

    }

    suspend fun getMovies(category: String): MoviesListResponse{
        return client.get("$BASE_URL/3/movie/$category"){
            addLanguageParam()
        }.body()
    }

    suspend fun getMoviesDetail(movieId: Int): MoviesResponse {
        return client.get("$BASE_URL/3/movie/$movieId"){
            addLanguageParam()
        }.body()
    }

    suspend fun getCredits(movieId: Int): CreditsListResponse{
        return client.get("$BASE_URL/3/movie/$movieId/credits"){
            addLanguageParam()
        }.body()
    }
    suspend fun getVideos(movieId: Int): VideosListResponse{
        return client.get("$BASE_URL/3/movie/$movieId/videos"){
            this.addLanguageParam()
        }.body()
    }
    }
    private fun HttpRequestBuilder.addLanguageParam(language: String = "pt-BR"){
        parameter("language",language)
    }
