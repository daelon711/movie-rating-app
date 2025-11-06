@Dao
interface UserDao {
    @Insert fun insert(user: User)
    @Query("SELECT * FROM User WHERE username=:name AND password=:pass")
    fun login(name: String, pass: String): User?
}
//:name - syntax that is replaced by defined function getting back the value