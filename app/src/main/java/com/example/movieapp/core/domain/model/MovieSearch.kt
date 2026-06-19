package com.example.movieapp.core.domain.model

data class MovieSearch(
    val id: Long,
    val voteAverage: Double = 0.0,
    val imageUrl: String = ""
)
