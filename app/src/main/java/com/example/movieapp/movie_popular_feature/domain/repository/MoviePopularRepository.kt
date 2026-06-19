package com.example.movieapp.movie_popular_feature.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    // ✅ Sênior: O método não pede mais configurações de infraestrutura, ele apenas entrega o fluxo limpo
    fun getPopularMovies(): Flow<PagingData<Movie>>
}