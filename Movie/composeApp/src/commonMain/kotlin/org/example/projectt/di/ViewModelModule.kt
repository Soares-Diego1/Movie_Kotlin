package org.example.projectt.di

import org.example.projectt.ui.cast.CastMembersViewModel
import org.example.projectt.ui.moviedetail.MovieDetailViewModel
import org.example.projectt.ui.movies.MoviesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesListViewModel(get()) }
    viewModel { MovieDetailViewModel(get(), get()) }

    viewModel { CastMembersViewModel(get(), get()) }
}