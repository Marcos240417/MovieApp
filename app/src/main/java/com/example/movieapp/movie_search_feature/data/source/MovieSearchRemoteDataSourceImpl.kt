package com.example.movieapp.movie_search_feature.data.source

import com.example.movieapp.core.data.remote.MovieService
import com.example.movieapp.core.data.remote.response.SearchResponse
import com.example.movieapp.core.paging.MovieSearchPagingSource
import com.example.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchRemoteDataSourceImpl(
    private val service: MovieService
) : MovieSearchRemoteDataSource {

    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(remoteDataSource = this, query = query)
    }

    override suspend fun getSearchMovies(
        page: Int,
        query: String
    ): SearchResponse {
        return service.searchMovies(query = query, page = page)
    }
}
