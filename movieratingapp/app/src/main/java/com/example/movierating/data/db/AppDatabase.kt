package com.example.movierating.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movierating.data.db.dao.MovieDao
import com.example.movierating.data.db.dao.RatingDao
import com.example.movierating.data.db.dao.UserDao
import com.example.movierating.data.db.entityRoom.MovieEntity
import com.example.movierating.data.db.entityRoom.UserEntity
import com.example.movierating.data.db.entityRoom.RatingEntity


//initializes ROOMDBS with daos

@Database(
    entities = [MovieEntity::class, UserEntity::class, RatingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun userDao(): UserDao
    abstract fun ratingDao(): RatingDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_app.db"
                ).build().also { INSTANCE = it }
            }
    }
}