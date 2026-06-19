package com.example.movieapp.movie_popular_feature.di

import com.example.movieapp.core.util.NetworkMonitor
import com.example.movieapp.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import com.example.movieapp.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import com.example.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.example.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import com.example.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUseCase
import com.example.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUseCaseImpl
import com.example.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Módulo de Injeção de Dependências alternativo usando a sintaxe tradicional de Lambdas.
 * ✅ PADRÃO SÊNIOR: Explicitamente vincula as interfaces usando o polimorfismo do Kotlin.
 */
val moviePopularFeatureModule = module {

    // 1. O Data Source: O Koin resolve a interface 'MoviePopularRemoteDataSource'
    // injetando a classe concreta 'MoviePopularRemoteDataSourceImpl'.
    single<MoviePopularRemoteDataSource> {
        MoviePopularRemoteDataSourceImpl(service = get())
    }

    // 2. O Repositório: O 'get()' busca automaticamente a dependência acima
    // dentro do próprio grafo do Koin.
    single<MoviePopularRepository> {
        MoviePopularRepositoryImpl(remoteDataSource = get())
    }



    // 3. O UseCase: Continua mapeado como factory (instâncias efêmeras).
    // O 'get()' resolve a interface do repositório de forma automática.
    factory<GetPopularMovieUseCase> {
        GetPopularMovieUseCaseImpl(repository = get())
    }

    viewModel {
        MoviePopularViewModel(getPopularMovieUseCase = get())
    }

    single { NetworkMonitor(context = get()) }
}