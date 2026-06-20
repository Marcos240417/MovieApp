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
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically o
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Avaliação do filme",
            tint = Color.Yellow, 
            modifier = Modifier.size(16.dp) 
        )

        Text(
            text = rate.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF000000) 
@Composable
private fun MovieRatePreview() {
    MovieRate(rate = 7.8)
}
