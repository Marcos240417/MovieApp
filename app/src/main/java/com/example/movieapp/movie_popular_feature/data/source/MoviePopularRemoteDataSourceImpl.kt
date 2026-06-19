package com.example.movieapp.movie_popular_feature.data.source

import com.example.movieapp.core.data.remote.MovieService
import com.example.movieapp.core.data.remote.response.MovieResponse
import com.example.movieapp.core.paging.MoviePagingSource
import com.example.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource

class MoviePopularRemoteDataSourceImpl(
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override fun getpopularMoviePagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovie(page: Int): MovieResponse {
        return service.getPopularMovies(page = page)
    }
}