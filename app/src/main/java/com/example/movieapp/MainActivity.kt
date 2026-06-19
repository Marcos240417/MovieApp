package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.core.presentation.MainScreen
import com.example.movieapp.ui.theme.MovieAppTheme

/**
 * A MainActivity é a janela principal que o sistema operacional exibe para o usuário.
 * No Jetpack Compose moderno, seguimos o padrão Single-Activity Architecture (Arquitetura de Atividade Única).
 * Isso significa que o app inteiro roda dentro desta única classe, mudando apenas as telas do Compose.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ ATIVAÇÃO SÊNIOR (Edge-to-Edge): Esta função diz ao Android para esticar o app
        // por baixo das barras do sistema (Barra de status no topo e barra de navegação por gestos na base).
        // Isso permite o visual imersivo de cinema que apps de streaming precisam.
        enableEdgeToEdge()

        // setContent: Bloco que substitui o antigo 'setContentView(R.layout.activity_main)' do XML.
        // É a ponte que transforma o código Kotlin em componentes gráficos do Compose na tela.
        setContent {
            MovieAppTheme {
                // ✅ Sênior: Sem Scaffold extra aqui. A responsabilidade de gerenciar
                // os paddings e a barra inferior vai ficar toda dentro da MainScreen.
                MainScreen(navController = rememberNavController())
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainPrev() {
    MainScreen(navController = rememberNavController())
}