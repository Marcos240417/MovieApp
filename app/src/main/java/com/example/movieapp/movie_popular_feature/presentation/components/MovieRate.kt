package com.example.movieapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieRate(
    rate: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp), // Espaçamento harmônico entre a estrela e o texto
        verticalAlignment = Alignment.CenterVertically // ✅ CORRIGIDO: Alinhamento vertical correto
    ) {
        // ✅ CORRIGIDO: Propriedades do Icon passadas corretamente dentro dos parênteses ()
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Avaliação do filme",
            tint = Color.Yellow, // Deixa a estrela amarela combinando com o tema do app
            modifier = Modifier.size(16.dp) // Define um tamanho controlado para o ícone
        )

        // ✅ ADICIONADO: Renderiza a nota real do filme (ex: 7.8)
        Text(
            text = rate.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White // Texto branco para contrastar com o fundo preto do app
        )
    }
}

// --- PREVIEW PARA O DESIGNER ---
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF000000) // Simula o fundo preto do app
@Composable
private fun MovieRatePreview() {
    MovieRate(rate = 7.8)
}