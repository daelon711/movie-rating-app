
package com.example.movierating.ui.screen

@Composable
fun WatchedScreen(ratedMovies: List<Pair<Movie, Int>>) { // to make pair from movie and int called in
    LazyColumn {
        items(ratedMovies) { (movie, rating) ->
            Row(Modifier.padding(16.dp)) {
                Text("${movie.title}: $rating★")
            }
        }
    }
}
