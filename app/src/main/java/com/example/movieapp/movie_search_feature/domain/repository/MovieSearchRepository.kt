package com.example.movieapp.movie_search_feature.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    // ✅ Sênior: Nome correto da ação e agnóstico a classes de configuração do Paging
    fun getSearchMovies(query: String): Flow<PagingData<MovieSearch>>
}