package com.example.movieapp.movie_search_feature.data.source

import com.example.movieapp.core.data.remote.MovieService
import com.example.movieapp.core.data.remote.response.SearchResponse
import com.example.movieapp.core.paging.MovieSearchPagingSource
import com.example.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource

class MovieSearchRemoteDataSourceImpl(
    private val service: MovieService
) : MovieSearchRemoteDataSource {

    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        // ✅ Instancia o motor do Paging 3 passando oDataSource e a palavra digitada (query)
        return MovieSearchPagingSource(remoteDataSource = this, query = query)
    }

    override suspend fun getSearchMovies(
        page: Int,
        query: String
    ): SearchResponse {
        // ✅ Consome diretamente o endpoint multi-search que você já tem no MovieService
        return service.searchMovies(query = query, page = page)
    }
}