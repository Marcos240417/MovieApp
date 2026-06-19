package com.example.movieapp.core.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.core.presentation.navigation.BottomNavigationBar
import com.example.movieapp.core.presentation.navigation.NavigationGraph
import com.example.movieapp.ui.theme.MovieAppTheme // ✅ Certifique-se de importar o tema correto do seu projeto

/**
 * A MainScreen é o "esqueleto" ou a "casca" visual fixa do seu aplicativo.
 */
@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier // ✅ Sênior: Adicionado o modificador padrão para permitir customizações externas se necessário
) {
    // --- O COMPONENTE ESTRUTURAL MÃE (Scaffold) ---
    Scaffold(
        modifier = modifier.fillMaxSize(), // ✅ Garante que o Scaffold ocupe a tela inteira do dispositivo
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        // --- O BLOCO DE CONTEÚDO (Lambda Content) ---
        NavigationGraph(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

// --- AMBIENTE DE PREVIEW DA APLICAÇÃO COMPLETA ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    // Instancia um NavController persistente para simular o comportamento de clique no preview
    val navController = rememberNavController()

    // ✅ Envelopado no tema do seu app para que o fundo preto e os detalhes amarelos apareçam no Android Studio
    MovieAppTheme {
        MainScreen(navController = navController)
    }
}