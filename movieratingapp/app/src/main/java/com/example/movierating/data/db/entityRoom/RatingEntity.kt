
package com.example.movierating.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "ratings")
data class RatingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val rating: Int,
    val userid: Int,
    val movieid: Int,

    )