package com.example.movieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme  // ✅ M3 usa darkColorScheme
import androidx.compose.material3.lightColorScheme // ✅ M3 usa lightColorScheme
import androidx.compose.runtime.Composable

// ✅ CORRIGIDO: Modificado de 'darkColors' para 'darkColorScheme' e 'primaryVariant' para 'primaryContainer'
private val DarkColorPalette = darkColorScheme(
    primary = BlackPrimary,
    primaryContainer = BlackSecondary,
)

// ✅ CORRIGIDO: Modificado de 'lightColors' para 'lightColorScheme'
private val LightColorPalette = lightColorScheme(
    primary = YellowSecondary,
    primaryContainer = BlackSecondary,
)

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // ✅ Ajustado o nome da variável para fazer sentido com o contexto do M3
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme, // ✅ CORRIGIDO: O parâmetro do MaterialTheme M3 chama-se 'colorScheme'
        typography = Typography,   // ✅ Agora vai compilar porque o tipo do objeto bate 100% com o M3
        shapes = Shapes,
        content = content
    )
}