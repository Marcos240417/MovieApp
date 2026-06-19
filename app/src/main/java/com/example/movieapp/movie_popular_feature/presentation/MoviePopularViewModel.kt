package com.example.movieapp.movie_popular_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUseCase
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel responsável por gerenciar e expor o fluxo de paginação para a UI.
 * ✅ PADRÃO SÊNIOR: Agnóstico ao mutableStateOf do Compose, deixando a coleta puramente reativa.
 */
class MoviePopularViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {

    // ✅ SÊNIOR: Expõe diretamente o fluxo reativo. A UI apenas observa esse ponto único.
    val moviesFlow: Flow<PagingData<Movie>> = getPopularMovieUseCase()
        .cachedIn(viewModelScope) // Garante o cache dos 20 filmes por página na memória do escopo
}