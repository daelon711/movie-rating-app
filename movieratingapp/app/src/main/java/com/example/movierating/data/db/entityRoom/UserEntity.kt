
package com.example.movierating.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) val id:Int,
    val username: String,
    val password: String
)