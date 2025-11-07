import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movierating.data.MovieEntity
import com.example.movierating.data.UserEntity
import com.example.movierating.data.RatingEntity


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