package com.example.movieapp.movie_popular_feature.domain.usecase

import androidx.paging.PagingData
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow

/**
 * Contrato do UseCase. Define a ação única de buscar filmes populares.
 * Excelente para ser mockado em testes unitários da camada de apresentação.
 */
interface GetPopularMovieUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

/**
 * Implementação do UseCase (Camada de Domínio).
 * ✅ SÊNIORES: Totalmente limpo de configurações de infraestrutura.
 */
class GetPopularMovieUseCaseImpl(
    private val repository: MoviePopularRepository
) : GetPopularMovieUseCase {

    override fun invoke(): Flow<PagingData<Movie>> {
        // ✅ CORRIGIDO: O UseCase apenas dispara a chamada. Quem sabe como
        // configurar o tamanho da página (PagingConfig) é o Repository lá no Data!
        return repository.getPopularMovies()
    }
}