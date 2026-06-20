package com.example.movieapp.movie_popular_feature.data.mapper

import com.example.movieapp.core.data.remote.model.MovieResult
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.util.ImageWidth
import com.example.movieapp.core.util.toTmdbImageUrl

fun List<MovieResult>.toMovie(): List<Movie> = map { movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.voteAverage,
        imageUrl = movieResult.posterPath.toTmdbImageUrl(ImageWidth.W500) ?: ""
    )
}
