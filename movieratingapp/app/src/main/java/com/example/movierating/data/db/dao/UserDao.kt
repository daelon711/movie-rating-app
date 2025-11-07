import androidx.room.Dao
import androidx.room.Insert
import com.example.movierating.data.UserEntity
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun upsert(user: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun findByUsername(username: String): UserEntity? // returns in userentitiy style, finds by username

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getById(id: Int): UserEntity? // finds by id
}
//:name - syntax that is replaced by defined function getting back the value