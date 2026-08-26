package com.example.movieapp.movie_search_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.MovieSearch
import com.example.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import com.example.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieSearchRepositoryImpl(
    private val remoteDataSource: MovieSearchRemoteDataSource
) : MovieSearchRepository {

    override fun getSearchMovies(query: String): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE, 
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.getSearchMoviePagingSource(query = query)
            }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
