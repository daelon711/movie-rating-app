
//initializes ROOMDBS with daos

@Database(entities = [Movie::class, User::class, Rating::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun userDao(): UserDao
    abstract fun ratingDao(): RatingDao
}
