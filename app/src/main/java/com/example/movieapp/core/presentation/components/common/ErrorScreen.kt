package com.example.movieapp.core.presentation.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Componente unificado para tratamento de falhas de comunicação e respostas HTTP.
 * ✅ PADRÃO SÊNIOR: Totalmente adaptável para cenários de tela cheia ou componentes reduzidos.
 */
@Composable
fun ErrorScreen(
    message: String,
    retry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        // ✅ ADICIONADO: Garante que o container ocupe o espaço proposto centralizando os eixos
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Texto descritivo do erro enviado pela API/Paging
        Text(
            text = message,
            maxLines = 3, // ✅ Expandido para 3 linhas para suportar mensagens de timeout completas
            overflow = TextOverflow.Ellipsis,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            // ✅ SÊNIOR: Substituído o 'white' fixo pelo padrão dinâmico do tema Material 3
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center, // ✅ Garante que o texto fique centralizado harmonicamente
            modifier = Modifier.fillMaxWidth()
        )

        // ✅ ADICIONADO: Spacer para criar o respiro visual dinâmico entre o texto e a ação
        Spacer(modifier = Modifier.height(16.dp))

        // 2. Botão de re-tentativa reativa
        Button(
            onClick = retry,
            // ✅ ADICIONADO: Customização de cores para bater com o visual Preto e Amarelo do seu app
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Recarregar",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
    }
}

// --- AMBIENTE DE PREVIEW ---
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ErrorScreenPreview() {
    // Simula uma falha real de timeout na listagem de filmes
    ErrorScreen(
        message = "Unable to resolve host \"api.themoviedb.org\": No address associated with hostname",
        retry = {},
        modifier = Modifier.fillMaxSize().background(Color.Black)
    )
}