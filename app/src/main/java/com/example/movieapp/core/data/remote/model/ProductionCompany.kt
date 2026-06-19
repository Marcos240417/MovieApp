package com.example.movieapp.core.data.remote.model

import kotlinx.serialization.SerialName

data class ProductionCompany(
    val id: Int,

    @SerialName("logo_path")
    val logoPath: String?,
    @SerialName("name")
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)