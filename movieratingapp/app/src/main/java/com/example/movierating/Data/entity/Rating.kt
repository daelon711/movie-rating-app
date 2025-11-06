
package com.example.movierating.Data

@Entity
data class Rating(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val rating: Int,
     val userid: Int,
     val movieid: Int,

)