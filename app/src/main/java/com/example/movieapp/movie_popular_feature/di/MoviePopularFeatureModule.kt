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

val moviePopularFeatureModule = module {

    single<MoviePopularRemoteDataSource> {
        MoviePopularRemoteDataSourceImpl(service = get())
    }

    single<MoviePopularRepository> {
        MoviePopularRepositoryImpl(remoteDataSource = get())
    }

    factory<GetPopularMovieUseCase> {
        GetPopularMovieUseCaseImpl(repository = get())
    }

    viewModel {
        MoviePopularViewModel(getPopularMovieUseCase = get())
    }

    single { NetworkMonitor(context = get()) }
}
