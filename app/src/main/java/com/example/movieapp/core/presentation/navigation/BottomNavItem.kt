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

// --- DEFINIÇÃO DOS DESTINOS TYPE-SAFE (ROTAS MODERNAS) ---
// A anotação @Serializable do KotlinX Serialization transforma esses 'data objects'
// em contratos de rota oficiais. O Jetpack Navigation converte essas classes em caminhos
// internos de metadados, eliminando completamente a necessidade de Strings livres ("popular_screen").
@Serializable data object MoviePopularDestination
@Serializable data object MovieSearchDestination
@Serializable data object MovieFavoriteDestination

/**
 * CLASSE SELADA GENÉRICA (Sealed Class): BottomNavItem
 * O modificador <out T : Any> utiliza COVARIÂNCIA. Isso significa que a classe pode receber
 * qualquer objeto que herde de 'Any' (a classe mãe do Kotlin) e tratá-lo de forma genérica e segura.
 */
sealed class BottomNavItem<out T : Any>(
    // @param:StringRes: Diz ao compilador para aplicar a validação do Lint estritamente ao
    // parâmetro do construtor, limpando o warning do Kotlin 2.1+ que vimos anteriormente.
    @param:StringRes val titleRes: Int,

    // O ícone do Material Design que será renderizado no topo da aba.
    val icon: ImageVector,

    // destination: É a instância real do objeto de rota tipado (ex: MoviePopularDestination).
    val destination: T,

    // destinationKClass: SACADA DE SÊNIOR! Como o 'destination' é genérico (<T>), a biblioteca
    // NavDestination do Compose muitas vezes não consegue ler o tipo em tempo de execução
    // devido à "Apagamento de Tipos" (Type Erasure) do Java/Kotlin. Guardar a referência física
    // por KClass<out Any> resolve isso e permite o uso da função nativa 'hasRoute()'.
    val destinationKClass: KClass<out Any>
) {
    // --- IMPLEMENTAÇÃO DOS ITENS DO MENU (DATA OBJECTS) ---

    // Cada item herda de BottomNavItem passando o seu tipo específico entre chaves <...>
    data object MoviePopular : BottomNavItem<MoviePopularDestination>(
        titleRes = R.string.menu_popular,
        icon = Icons.Default.Movie,
        destination = MoviePopularDestination, // Passa o objeto
        destinationKClass = MoviePopularDestination::class // Passa o metadado da classe (.class do Kotlin)
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