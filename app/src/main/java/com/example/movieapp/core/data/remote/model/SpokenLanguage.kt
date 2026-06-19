package com.example.movieapp.core.data.remote.model

import kotlinx.serialization.SerialName

data class SpokenLanguage(

    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    @SerialName("name")
    val name: String
)