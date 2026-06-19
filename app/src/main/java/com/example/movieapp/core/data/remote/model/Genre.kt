package com.example.movieapp.core.data.remote.model

import kotlinx.serialization.SerialName

data class Genre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)