package com.example.movieapp.movie_popular_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.movie_popular_feature.presentation.components.MovieContent
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.ui.theme.black
import com.example.movieapp.ui.theme.white
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviePopularScreen(
    movies: LazyPagingItems<Movie>,
    navigateToDetailMovie: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.popular_movies),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
        }
    ) { paddingValues ->
        MovieContent(
            pagingMovies = movies,
            paddingValues = paddingValues,
            onClick = { movieId ->
                Timber.tag("CLIQUE_FILME").d("Usuário clicou no filme com ID: $movieId")
                navigateToDetailMovie(movieId)
            }
        )
    }
}
