// shows top movies with lazycolumn with moving cards and clickable stars, gets data from viewmodel

package com.example.movierating.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movierating.data.viewModel.MainViewModel
import com.example.movierating.ui.components.MovieCard
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TopAppBar

//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    vm: MainViewModel,
    userId: Int,
    onGoWatched: () -> Unit
) {
    val movies by vm.movies.collectAsState()
    LaunchedEffect(Unit) { vm.initFetchOnce() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top 5") },
                actions = {
                    TextButton(onClick = onGoWatched) { Text("My Ratings") }
                }
            )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding).padding(12.dp)) {
            items(movies) { m ->
                MovieCard(movie = m) { stars ->
                    vm.rate(userId = userId, movieId = m.id, rating = stars)
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
