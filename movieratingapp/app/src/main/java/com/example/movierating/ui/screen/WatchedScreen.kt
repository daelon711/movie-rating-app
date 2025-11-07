// movies rated by users, shows db via viewmodel


package com.example.movierating.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movierating.data.viewModel.WatchedViewModel

@Composable
fun WatchedScreen(vm: WatchedViewModel, onBack: () -> Unit) {
    val items by vm.rated.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Ratings") },
                navigationIcon = { TextButton(onClick = onBack) { Text("< Back") } }
            )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding).padding(12.dp)) {
            items(items) { row ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Text(row.title)
                        Text("Rating: ${row.rating}/5")
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

