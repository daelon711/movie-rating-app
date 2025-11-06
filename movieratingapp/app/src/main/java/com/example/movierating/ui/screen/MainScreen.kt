
package com.example.movierating.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Movie(val id: Int, val title: String, val posterUrl: String)

@Composable
fun MovieListScreen(
    movies: List<Movie>,
    onMovieRated: (Movie, Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {//makes it scrollable up and down
        items(movies) { movie ->
            MovieItem(movie, onMovieRated)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieRated: (Movie, Int) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
        Row {
            (1..5).forEach { star -> // 5 stars for each we click we get onclick of movie we rate
                IconButton(onClick = { onMovieRated(movie, star) }) {
                    Icon(Icons.Default.Star, contentDescription = "$star stars")
                }
            }
        }
        Divider()
    }
}
