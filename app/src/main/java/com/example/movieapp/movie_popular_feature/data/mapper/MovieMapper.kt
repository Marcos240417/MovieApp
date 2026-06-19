package com.example.movieapp.movie_popular_feature.data.mapper

import com.example.movieapp.core.data.remote.model.MovieResult
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.util.ImageWidth
import com.example.movieapp.core.util.toTmdbImageUrl

/**
 * Converte uma lista de DTOs de rede (MovieResult) em uma lista de modelos de Domínio (Movie).
 * ✅ CORRIGIDO: Adicionado parênteses () e declaração explícita do tipo de retorno ': List<Movie>'
 */
fun List<MovieResult>.toMovie(): List<Movie> = map { movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.voteAverage,
        // ✅ CORRIGIDO: Utilizando a nossa extensão unificada e inteligente de URL com tamanho padrão W500
        imageUrl = movieResult.posterPath.toTmdbImageUrl(ImageWidth.W500) ?: ""
    )
}