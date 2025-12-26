package org.example.projectt


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavType // <-- IMPORTAR ISTO
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument // <-- IMPORTAR ISTO
import org.example.projectt.di.dataModule
import org.example.projectt.di.networkModule
import org.example.projectt.di.viewModelModule
import org.example.projectt.navigation.AppRoutes
import org.example.projectt.ui.moviedetail.MovieDetailRoute
import org.example.projectt.ui.movies.MoviesListRoute
import org.example.projectt.ui.theme.MoviesAppTheme


import org.example.projectt.ui.login.LoginScreen
import org.example.projectt.ui.register.RegisterScreen
import org.example.projectt.ui.cast.CastMembersRoute

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(networkModule, dataModule, viewModelModule)
        }
    ) {
        MoviesAppTheme {
            val navController = rememberNavController()


            val auth = remember { Firebase.auth }
            val startDestination = if (auth.currentUser != null) {

                AppRoutes.MoviesList
            } else {

                AppRoutes.Login
            }




            NavHost(navController, startDestination = startDestination) {


                composable<AppRoutes.Login> {
                    LoginScreen(

                        onLoginSuccess = {
                            navController.navigate(AppRoutes.MoviesList) {
                                popUpTo(AppRoutes.Login) { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            navController.navigate(AppRoutes.Register)
                        },
                        onNeedHelpClick = {
                            // TODO: Lógica para "precisa de ajudar?"
                        }
                    )
                }


                composable<AppRoutes.Register> {
                    RegisterScreen(
                        onRegisterSuccess = {

                            navController.popBackStack()
                        },
                        onNavigateToLogin = {
                            navController.popBackStack() // Volta para a tela anterior (Login)
                        }
                    )
                }


                composable<AppRoutes.MoviesList> {
                    MoviesListRoute(
                        navigateToMovieDetail = { movieId ->
                            navController.navigate(AppRoutes.MovieDetail(movieId))
                        }
                    )
                }


                composable<AppRoutes.MovieDetail> { backStackEntry ->
                    MovieDetailRoute(
                        navigateBack = { navController.popBackStack() },
                        navigateToCast = { movieId ->

                            navController.navigate("cast/$movieId")
                        }
                    )
                }


                composable(
                    route = "cast/{movieId}",
                    arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                ) {
                    CastMembersRoute(
                        navigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}