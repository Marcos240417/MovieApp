package com.example.movieapp.movie_popular_feature.domain.usecase

import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow

interface GetPopularMovieUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

class GetPopularMovieUseCaseImpl(
    private val repository: MoviePopularRepository
) : GetPopularMovieUseCase {

    override fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies()
    }
}
