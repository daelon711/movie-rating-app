//erusable card for displaiying movies info and stars used on watched and  main screen

// ui/main/MovieCard.kt
package com.example.movierating.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.movierating.data.MovieEntity
import coil.compose.rememberAsyncImagePainter

@Composable
fun MovieCard(movie: MovieEntity, onRate: (Int) -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(12.dp)) {
            Image(
                painter = rememberAsyncImagePainter(movie.posterUrl),
                contentDescription = movie.title,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(movie.title, style = MaterialTheme.typography.titleMedium)
                Row {
                    (1..5).forEach { star ->
                        TextButton(onClick = { onRate(star) }) { Text("★") }
                    }
                }
            }
        }
    }
}
