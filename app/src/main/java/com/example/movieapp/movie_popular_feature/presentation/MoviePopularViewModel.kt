package com.example.movieapp.movie_popular_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.movie_popular_feature.domain.usecase.GetPopularMovieUseCase
import kotlinx.coroutines.flow.Flow

class MoviePopularViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel() {

    val moviesFlow: Flow<PagingData<Movie>> = getPopularMovieUseCase()
        .cachedIn(viewModelScope) 
}
