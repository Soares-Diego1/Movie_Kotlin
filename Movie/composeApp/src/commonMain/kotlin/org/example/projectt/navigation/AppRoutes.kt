

package org.example.projectt.navigation
import kotlinx.serialization.Serializable

sealed interface AppRoutes{

    @Serializable
    data object Login: AppRoutes

    @Serializable
    data object Register: AppRoutes

    @Serializable
    data object MoviesList: AppRoutes

    @Serializable
    data class  MovieDetail(val id:Int): AppRoutes
}