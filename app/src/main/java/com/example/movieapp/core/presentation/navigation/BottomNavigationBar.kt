package com.example.movieapp.core.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem.MoviePopular,
        BottomNavItem.MovieSearch,
        BottomNavItem.MovieFavorite
    )

    // Observa a pilha de telas reativamente
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // ✅ MIGRADO: 'NavigationBar' substitui 'BottomNavigation' (Material 3)
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Black // ✅ Mantida a sua cor preta de fundo
    ) {
        items.forEach { item ->

            // ✅ CORRIGIDO: Validação sênior e type-safe se a aba está ativa
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(item.destination::class)
            } == true

            // ✅ MIGRADO: 'NavigationBarItem' substitui 'BottomNavigationItem'
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.destination) {
                        // Limpa a pilha para evitar acúmulo de telas ao trocar de aba
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.titleRes),
                    )
                },
                label = {
                    Text(text = stringResource(id = item.titleRes))
                },
                // ✅ MIGRADO: Configuração moderna das suas cores personalizadas no Material 3
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Yellow,     // Ícone amarelo quando ativo
                    selectedTextColor = Color.Yellow,     // Texto amarelo quando ativo
                    unselectedIconColor = Color.Gray,     // Ícone cinza quando inativo
                    unselectedTextColor = Color.Gray,     // Texto cinza quando inativo
                    indicatorColor = Color.DarkGray.copy(alpha = 0.3f) // Cor da "pílula" de seleção de fundo
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BottomNavigationBarPreview() {
    val fakeNavController = rememberNavController()

    MovieAppTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = fakeNavController)
            }
        ) { innerPadding -> // 🚨 O warning nasce aqui se você não usar a variável
            // ✅ CORRIGIDO: Criamos um Box ou Modifier aplicando o padding de segurança
            androidx.compose.foundation.layout.Box(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}