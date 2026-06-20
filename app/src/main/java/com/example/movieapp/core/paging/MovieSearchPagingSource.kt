package com.example.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.core.domain.model.MovieSearch
import com.example.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class MovieSearchPagingSource(
    private val remoteDataSource: MovieSearchRemoteDataSource,
    private val query: String
) : PagingSource<Int, MovieSearch>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: LIMIT_PAGE_START

            val response = remoteDataSource.getSearchMovies(page = pageNumber, query = query)
            val moviesSearch = response.results.toMovieSearch()

            val totalPages = response.totalPages

            LoadResult.Page(
                data = moviesSearch,
                prevKey = if (pageNumber == LIMIT_PAGE_START) null else pageNumber - 1,
                nextKey = if (moviesSearch.isEmpty() || pageNumber >= totalPages) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val LIMIT_PAGE_START = 1
    }
}
