
package com.example.movierating.Data

@Entity
data class User {
    @PrimaryKey (autoGenerate = true) val id:Int,
            val username: String,
                    val password: String
}