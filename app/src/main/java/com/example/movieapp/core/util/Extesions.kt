package com.example.movieapp.core.util

import com.example.movieapp.BuildConfig

enum class ImageWidth(val value: String) {
    W500("w500"),
    W780("w780"),
    ORIGINAL("original")
}

fun String?.toTmdbImageUrl(width: ImageWidth = ImageWidth.W500): String? {
    if (this.isNullOrBlank()) return null

    val cleanPath = this.removePrefix("/")
    val cleanBaseUrl = BuildConfig.BASE_URL_IMAGE.removeSuffix("/")

    return "$cleanBaseUrl/${width.value}/$cleanPath"
}
