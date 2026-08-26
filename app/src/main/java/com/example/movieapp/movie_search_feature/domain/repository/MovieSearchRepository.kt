package com.example.movieapp.movie_search_feature.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getSearchMovies(query: String): Flow<PagingData<MovieSearch>>
}
