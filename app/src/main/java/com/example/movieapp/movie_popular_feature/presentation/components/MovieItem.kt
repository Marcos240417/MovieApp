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
import com.example.movieapp.R 

@Composable
fun MovieItem(
    voteAverage: Double,
    imageUrl: String,
    id: Int,
    onClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(160.dp)
            .height(230.dp)
            .padding(4.dp)
            .clickable { onClick(id) } 
    ) {

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .error(R.drawable.ic_error_image)
                    .placeholder(R.drawable.ic_placeholder)
                    .build(),
                contentDescription = "Poster do Filme",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop 
            )
        }

        MovieRate(
            rate = voteAverage,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f) 
                .padding(start = 8.dp, bottom = 8.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.7f), 
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 6.dp, vertical = 2.dp) 
        )
    }
}


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
