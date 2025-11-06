
package com.example.movierating.Data

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val pictureUrl: String
)