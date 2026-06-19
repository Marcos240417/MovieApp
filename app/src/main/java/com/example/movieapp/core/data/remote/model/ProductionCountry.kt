package com.example.movieapp.core.data.remote.model

import kotlinx.serialization.SerialName

data class ProductionCountry(

    @SerialName("iso_3166_1")
    val iso31661: String,
    @SerialName("name")
    val name: String
)