package com.example.movieapp.core.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieapp.R
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable data object MoviePopularDestination
@Serializable data object MovieSearchDestination
@Serializable data object MovieFavoriteDestination

sealed class BottomNavItem<out T : Any>(
    @param:StringRes val titleRes: Int,
    val icon: ImageVector,

    val destination: T,

    val destinationKClass: KClass<out Any>
) {
    data object MoviePopular : BottomNavItem<MoviePopularDestination>(
        titleRes = R.string.menu_popular,
        icon = Icons.Default.Movie,
        destination = MoviePopularDestination, 
        destinationKClass = MoviePopularDestination::class 
    )

    data object MovieSearch : BottomNavItem<MovieSearchDestination>(
        titleRes = R.string.menu_search,
        icon = Icons.Default.Search,
        destination = MovieSearchDestination,
        destinationKClass = MovieSearchDestination::class
    )

    data object MovieFavorite : BottomNavItem<MovieFavoriteDestination>(
        titleRes = R.string.menu_favorite,
        icon = Icons.Default.Favorite,
        destination = MovieFavoriteDestination,
        destinationKClass = MovieFavoriteDestination::class
    )
}
