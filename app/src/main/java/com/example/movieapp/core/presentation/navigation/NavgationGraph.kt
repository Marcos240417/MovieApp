package com.example.movieapp.core.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import com.example.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = MoviePopularDestination,
        modifier = Modifier
    ) {

        composable<MoviePopularDestination> {
            val viewModel: MoviePopularViewModel = koinViewModel()
            val pagingMovies = viewModel.moviesFlow.collectAsLazyPagingItems()

            MoviePopularScreen(
                movies = pagingMovies,
                navigateToDetailMovie = { movieId ->
                    Timber.tag("CLIQUE_FILME").d("Usuário clicou no filme com ID: $movieId")
                }
            )
        }
        composable<MovieSearchDestination> {
            Text(text = "Tela de Pesquisa")
        }

        composable<MovieFavoriteDestination> {
            Text(text = "Tela de Favoritos")
        }
    }
}
