package com.example.movieapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R // ✅ Garante o acesso aos drawables do seu projeto

/**
 * Componente visual que renderiza o card individual de cada filme.
 * Utiliza o Coil com tratamento de imagem profissional e sobreposição da nota do filme.
 */
@Composable
fun MovieItem(
    voteAverage: Double,
    imageUrl: String,
    id: Int,
    onClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // ✅ SÊNIOR: Dimensões fixadas na raiz garantem o comportamento de grade uniforme (Grid)
    Box(
        modifier = modifier
            .width(160.dp)
            .height(230.dp)
            .padding(4.dp)
            .clickable { onClick(id) } // ✅ Área de clique expandida para todo o container
    ) {

        // 1. O Card que encapsula o poster do filme
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            // ✅ CORRIGIDO: Sintaxe oficial do Material 3 para elevação de cards
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            // ✅ INTEGRADO: Estrutura sênior do Coil com transição de fade e tratamento de falhas
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    // Substitua pelos nomes exatos dos seus arquivos xml/png em res/drawable se necessário:
                    .error(R.drawable.ic_error_image)
                    .placeholder(R.drawable.ic_placeholder)
                    .build(),
                contentDescription = "Poster do Filme",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop // ✅ Preenche o card proporcionalmente sem achatar a imagem
            )
        }

        // 2. A Tag de nota flutuante (MovieRate) posicionada por cima da imagem
        MovieRate(
            rate = voteAverage,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f) // Força o componente a ficar na camada superior
                .padding(start = 8.dp, bottom = 8.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.7f), // Visual transparente premium estilo streaming
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 6.dp, vertical = 2.dp) // Respiro interno para o texto e a estrela
        )
    }
}

// --- AMBIENTE DE DESIGN (PREVIEW) ---
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun MovieItemPreview() {
    MovieItem(
        voteAverage = 7.4,
        imageUrl = "https://image.tmdb.org/t/p/w500/1E5baAaE7Ziiqj4nN6vN60YwKwH.jpg",
        id = 1,
        onClick = {}
    )
}