import androidx.room.Dao

import androidx.room.Insert
import androidx.room.Query
import com.example.movierating.data.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies LIMIT 5")
    fun getTop5(): Flow<List<MovieEntity>>

    @Query("SELECT COUNT(*) FROM movies")
    suspend fun count(): Int
}