

//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
@Dao
interface MovieDao {
    @Insert fun insert(movie: Movie)
    @Query("SELECT * FROM Movie LIMIT 5") fun getTop5(): List<Movie>
}