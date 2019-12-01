package data.local.Dao
import androidx.lifecycle.LiveData
import androidx.room.*
import data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE  id = :id")
    fun getUserById(id: Long): LiveData<User>

    @Query("SELECT * FROM users WHERE email= :email")
    fun getUserByEmail(email: String): LiveData<User>

    @Query("SELECT * FROM users WHERE first_name= :first_name")
    fun getUserByName(first_name: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getUser():LiveData<List<User>>


}