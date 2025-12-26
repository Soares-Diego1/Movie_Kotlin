package org.example.projectt.di

import org.example.projectt.data.repository.MoviesRepository
import org.koin.dsl.module

val dataModule = module {
    factory { MoviesRepository (get())}
}