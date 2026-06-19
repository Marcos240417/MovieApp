package com.example.movieapp.core.util

import com.example.movieapp.BuildConfig

// ✅ Sênior: Enum para centralizar as dimensões suportadas pelo TMDB de forma tipada
enum class ImageWidth(val value: String) {
    W500("w500"),
    W780("w780"),
    ORIGINAL("original")
}
/**
 * Monta a URL completa para imagens do TMDB de forma segura.
 * Se o path for nulo ou vazio, retorna null imediatamente, evitando chamadas web inválidas no Coil.
 * * Exemplo de uso no Compose:
 * movie.posterPath.toTmdbImageUrl(ImageWidth.W500)
 */
fun String?.toTmdbImageUrl(width: ImageWidth = ImageWidth.W500): String? {
    if (this.isNullOrBlank()) return null

    // Remove barras duplicadas caso a constante ou o path venham com barras extras
    val cleanPath = this.removePrefix("/")
    val cleanBaseUrl = BuildConfig.BASE_URL_IMAGE.removeSuffix("/")

    return "$cleanBaseUrl/${width.value}/$cleanPath"
}