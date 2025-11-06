@Dao
interface RatingDao {
    @Insert fun insert(rating: Rating)
    @Query("SELECT * FROM Movie INNER JOIN Rating ON Movie.id = Rating.movieId WHERE Rating.userId=:userId")
    fun getUserRatings(userId: Int): List<Movie>
}

