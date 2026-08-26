package com.example.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.movie_popular_feature.data.mapper.toMovie
import com.example.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import retrofit2.HttpException
import java.io.IOException


class MoviePagingSource(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: LIMIT_PAGE_START
            val response = remoteDataSource.getPopularMovie(page = pageNumber)
            val movies = response.results.toMovie()
            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == LIMIT_PAGE_START) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val LIMIT_PAGE_START = 1
    }
}
