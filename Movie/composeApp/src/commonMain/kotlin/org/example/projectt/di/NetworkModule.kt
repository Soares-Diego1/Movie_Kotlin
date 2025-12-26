package org.example.projectt.di

import org.example.projectt.data.network.KtorClient
import org.koin.dsl.module

val networkModule= module {

    single { KtorClient()}

}
