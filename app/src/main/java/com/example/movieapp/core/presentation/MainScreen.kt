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
import com.example.movieapp.ui.theme.MovieAppTheme 

@Composable
fun MainScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier 
) {
    Scaffold(
        modifier = modifier.fillMaxSize(), 
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavigationGraph(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    val navController = rememberNavController()
    MovieAppTheme {
        MainScreen(navController = navController)
    }
}
