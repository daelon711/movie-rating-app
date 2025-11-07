// endpoints fetching movies and posting ratings like api route in backend
//uses retrofit
//each function has get or post with endpoint
//returns data classes with json response
//retrofit api basically

package com.example.movierating.data.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Body
import retrofit2.http.Path

// object that transfers data for each movie from api
//matches backend's movie name conventions  JSON, entity part
// like id title posterurl
// this is for GET
data class MovieData(
    val id: Int,
    val title: String,
    val posterUrl: String
)

// data class for sending ratings
//this is for POST
data class RatingSend(
    val movieId: Int,
    val userId: Int, // this is for sending to know which user it belongs to
    val rating: Int

)
//get or post
data class RatingData(
    val id: Int,
    val movieId: Int,
    val userId: Int,
    val rating: Int
)


// For user registration or login (POST)
data class UserSend(
    val username: String,
    val password: String
)

// For getting user info (GET)
data class UserData(
    val id: Int,
    val username: String
)

//RETORFIT  interface for endpoints, so like json setup like in backend
// name the filename for interface
interface MovieApiService{
    //fetch movies path, give api key
    @GET("movies/top5")
    suspend fun getTopMovies(
        @Header("Authorization") apiKey: String // param that needs to be passed
    ): List<MovieData> // thing that is returned

    //send rating of movie
    @POST("movies/{id}/rate")
    suspend fun rateMovie(
        @Path("id") movieId: Int, // defines which movie id it has
        @Header("Authorization") apiKey: String,
        @Body  rating: RatingSend
    ): RatingData

    @GET("users/{userId}/ratings")
    suspend fun getUserRatings(
        @Path("userId") userId: Int, // retrofit replaces with {userid} in path later
    ): List<RatingData>

    @POST("login")
    suspend fun loginUser(
        @Body user: UserSend // used in viewmodel to assemble user send with given params or passw and username
    ): UserData //return username and id to use it in watched page

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Int // according to user id return userdata
    ): UserData
}

// each method matches route for getting movies or rating movies, api kkey is sent as header for aautherntc
//dataclasses match json of backend model built for sends and receives