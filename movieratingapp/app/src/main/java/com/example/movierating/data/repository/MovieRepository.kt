// fetches movies from api, saves to db, gives flows to viewmodels?, has rating saving so -> db  + api

// calls api via movieapiservice, saves and fetches movies and ratings in room, gives suspend functions ?
//viewmodels call functions from here
//this calls movieapiservice and daos needed
//

package com.example.movierating.data.repository


import com.example.movierating.data.db.dao.MovieDao
import com.example.movierating.data.db.dao.RatingDao
import com.example.movierating.data.db.dao.UserDao
import com.example.movierating.data.db.dao.RatedMovieRow
import com.example.movierating.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import com.example.movierating.data.db.entityRoom.MovieEntity
import com.example.movierating.data.db.entityRoom.RatingEntity
import com.example.movierating.data.db.entityRoom.UserEntity

import com.example.movierating.data.network.MovieData
import com.example.movierating.data.network.RatingSend
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val movieDao: MovieDao,
    private val ratingDao: RatingDao,
    private val userDao: UserDao,
    private val apiKey: String
) {
    // suspend means this function can be paused and resumed within coroutines,
    // and must be called from a coroutine or another suspend function.
    suspend fun refreshTopMovies() {
        withContext(Dispatchers.IO) {
            val movies: List<MovieData> = ApiClient.apiService.getTopMovies(apiKey)
            val entities = movies.map { dto ->
                MovieEntity(
                    id = dto.id,
                    title = dto.title,
                    posterUrl = dto.posterUrl
                )
            }
            movieDao.insertAll(entities)
        }
    }

    fun getTopMoviesFlow(): Flow<List<MovieEntity>> = movieDao.getTop5()

    suspend fun ensureMoviesFetchedOnce() {
        if (movieDao.count() == 0) {
            refreshTopMovies()
        }
    }

    suspend fun login(username: String, password: String): UserEntity {
        return withContext(Dispatchers.IO) {
            val userData = ApiClient.apiService.loginUser(
                com.example.movierating.data.network.UserSend(username, password)
            )
            val entity = UserEntity(id = userData.id, username = userData.username, password=userData.password)
            userDao.upsert(entity)
            entity
        }
    }

    suspend fun rateMovie(userId: Int, movieId: Int, rating: Int) {
        withContext(Dispatchers.IO) {
            ApiClient.apiService.rateMovie( // why is ratemovie called here
                movieId = movieId,
                apiKey = apiKey,
                rating = RatingSend(movieId = movieId, userId = userId, rating = rating)
            )
            ratingDao.insertOrUpdate(
                RatingEntity(userId = userId, movieId = movieId, rating = rating)
            )
        }
    }

    fun getUserRatedMovies(userId: Int): Flow<List<RatedMovieRow>> =
        ratingDao.getRatedMovies(userId)
}



