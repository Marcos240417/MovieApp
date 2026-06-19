package com.example.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.movie_popular_feature.data.mapper.toMovie
import com.example.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

/**
 * Motor de Paginação automatizado do Paging 3.
 * Ele controla o fluxo de páginas sob demanda (page = 1, 2, 3...) enviadas para a UI.
 */
class MoviePagingSource(
    private val remoteDataSource: MoviePopularRemoteDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            // Se o params.key for nulo, significa que é a primeira abertura (página 1)
            val pageNumber = params.key ?: LIMIT_PAGE_START
            // Dispara a requisição para o Data Source
            val response = remoteDataSource.getPopularMovie(page = pageNumber)
            // ✅ CORRIGIDO: Utiliza o seu mapper para converter List<MovieResult> em List<Movie>
            val movies = response.results.toMovie()
            // ✅ CORRIGIDO: Retorna a página preenchida calculando o avanço e retrocesso dos índices
            LoadResult.Page(
                data = movies,
                // Se estiver na página 1, o prevKey é nulo (não dá para voltar atrás do início)
                prevKey = if (pageNumber == LIMIT_PAGE_START) null else pageNumber - 1,
                // Se a lista retornada pela API vier vazia, significa que chegamos ao fim dos dados da API
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            // ✅ CORRIGIDO: Captura falhas de conexão física (Sem internet, timeout)
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            // ✅ CORRIGIDO: Captura erros de resposta do servidor TMDB (Ex: Erro 401, 500)
            LoadResult.Error(exception)
        }
    }

    /**
     * O getRefreshKey ajuda o Paging 3 a se posicionar caso o app sofra uma invalidação
     * (como um Swipe-to-Refresh). Ele tenta buscar o índice da página mais próxima da visão atual do usuário.
     */
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val LIMIT_PAGE_START = 1
    }
}