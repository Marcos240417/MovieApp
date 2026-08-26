package com.example.movieapp.movie_popular_feature.presentation.state

import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class MoviePopularState(
   val movies: Flow<PagingData<Movie>>
)
