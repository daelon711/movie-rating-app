// endpoints fetching movies and posting ratings like api route in backend
//uses retrofit
//each function has get or post with endpoint
//returns data classes with json response

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
data class RatingData(
    val movieId: Int,
    val userId: Int,
    val rating: Int

)

//RETORFIT  interface for endpoints, so like json setup like in backend
// name the filename for interface
interface MovieApiService{
    //fetch movies path, give api key
    @GET("movies/top5")
    suspend fun getTopMovies(
        @Header("Authorization") apiKey: String
    ): List<MovieData>

    //send rating of movie
    @POST("movies/{id}/rate")
    suspend fun rateMovie{
        @Path("id") movieId: Int,
        @Header("Authorization") apiKey: String,
        @Body  rating: RatingData
    }: Unit // no body returning

}