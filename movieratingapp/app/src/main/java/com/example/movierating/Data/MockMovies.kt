package com.example.movierating.Data

data class MovieEntity(
    val id: Int = 0,
    val title: String,
    val description: String,
    val rating: Float = 0f,
    val posterUrl: String,

    ) {
}

class MockDataMovies(){
    fun  loadMovies() : List<MovieEntity>{
        return listOf<MovieEntity>(
            MovieEntity(1, "Movies at the movies","Movie1", 10f, "https://m.media-amazon.com/images/S/pv-target-images/b92d2865829416e35e7102a3934a2ee745f3b903a95678710442d4299d86f39c._SX1080_FMjpg_.jpg"),
            MovieEntity(2, "Movies at the movies again","Movie2", 10.0f, "https://www.aelpublications.com/wp-content/uploads/2017/03/TwelveAngryMenCover.jpg"),
        )
    }
}
