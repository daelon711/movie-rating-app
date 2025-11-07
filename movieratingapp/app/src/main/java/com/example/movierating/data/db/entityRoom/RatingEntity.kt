package com.example.movierating.data.db.entityRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "ratings")
data class RatingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val rating: Int,
    val userId: Int,
    val movieId: Int,

    )