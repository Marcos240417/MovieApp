package com.example.movieapp.movie_popular_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.paging.MoviePagingSource
import com.example.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.example.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRepositoryImpl(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {

    // ✅ CORRIGIDO: Removido o '(pagingConfig: PagingConfig)' da assinatura para bater com a interface
    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,          // O limite de 20 por página morre e nasce aqui dentro de forma estrita
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource = remoteDataSource)
            }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}