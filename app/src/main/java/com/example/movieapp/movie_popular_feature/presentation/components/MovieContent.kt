package com.example.movieapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.presentation.components.common.ErrorScreen
import com.example.movieapp.core.presentation.components.common.LoadingView

@Composable
fun MovieContent(
    pagingMovies: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onClick: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                count = pagingMovies.itemCount,
                key = { index ->
                    val movie = pagingMovies[index]
                    movie?.let { "${it.id}_$index" } ?: index
                }
            ) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        onClick = { movieId -> onClick(movieId) }
                    )
                }
            }

            if (pagingMovies.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    LoadingView(modifier = Modifier.fillMaxWidth())
                }
            }

            if (pagingMovies.loadState.append is LoadState.Error) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Button(
                        onClick = { pagingMovies.retry() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.DarkGray,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = "Falha ao carregar mais filmes. Toque para tentar novamente.")
                    }
                }
            }
        }

        if (pagingMovies.loadState.refresh is LoadState.Loading) {
            LoadingView(modifier = Modifier.align(Alignment.Center))
        }
        if (pagingMovies.loadState.refresh is LoadState.Error) {
            val errorState = pagingMovies.loadState.refresh as LoadState.Error
            ErrorScreen(
                message = errorState.error.localizedMessage ?: "Erro ao carregar o catálogo",
                retry = { pagingMovies.refresh() },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

        /*// ---  BLOQUEIO INSTANTÂNEO DE QUEDA DE SINAL ---
        // Se a internet cair, a ErrorScreen cobre os filmes na mesma fração de segundo!
        //Manter apenas para usos apropriado nos App de banco e coisas parecidas.
        if (!isOnline) {
            ErrorScreen(
                message = "Sua conexão com a internet caiu. Verifique o Wi-Fi ou dados móveis e tente novamente.",
                retry = { pagingMovies.retry() },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f)) // Fundo escuro premium preservando os posters atrás
            )
        }
    }
}*/
