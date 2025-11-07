import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movierating.data.MovieEntity
import com.example.movierating.data.RatingEntity
import kotlinx.coroutines.flow.Flow

data class RatedMovieRow(
    val movieId: Int,
    val title: String,
    val posterUrl: String,
    val rating: Int
)

@Dao
interface RatingDao {
    @Insert
    suspend fun insertOrUpdate(rating: RatingEntity)

    //matches data class up there with aliases
    @Query("""
        SELECT m.id AS movieId, m.title AS title, m.posterUrl AS posterUrl, r.rating AS rating 
        FROM ratings r
        JOIN movies m ON m.id = r.movieId
        WHERE r.userId = :userId
        ORDER BY r.id DESC
    """)
    fun getRatedMovies(userId: Int): Flow<List<RatedMovieRow>> // replaces userid
}