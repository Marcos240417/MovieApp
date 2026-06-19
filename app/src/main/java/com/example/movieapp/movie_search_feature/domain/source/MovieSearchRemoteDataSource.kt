package com.example.movieapp.movie_search_feature.domain.source

import com.example.movieapp.core.data.remote.response.SearchResponse
import com.example.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): SearchResponse

}