package repository

import data.remote.WebService.UserService
import androidx.lifecycle.LiveData
import data.local.Dao.UserDao
import data.model.User
import data.model.UserWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val userService: UserService, private val userDao: UserDao) {

    private fun saveUsersToLocal(users: List<User>) {
        for (user in users) {
            userDao.insertUser(user)
        }
    }

    private fun saveUserToLocal(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUserFromLocal(): LiveData<List<User>> =
        withContext(Dispatchers.IO) {
            userDao.getUser()
        }


    suspend fun getUsers(): Response<UserWrapper> {
        lateinit var users: Response<UserWrapper>
        withContext(Dispatchers.IO) {
            val allUsers = userService.getUsersAsync().await()
            saveUsersToLocal(allUsers.body()!!.embeddedUsers.allUsers)
            withContext(Dispatchers.Main) {
                users = allUsers
            }
        }

        return users
    }

    suspend fun getUserById(id: Long): Response<User> =
        withContext(Dispatchers.IO) {
            userService.getUserAsync(id).await()
        }

    suspend fun insertUser(user: User): Response<Void> =
        withContext(Dispatchers.IO) {
            saveUserToLocal(user)
            userService.insertUserAsync(user).await()
        }

    suspend fun updateUser(id: Long, user: User): Response<User> =
        withContext(Dispatchers.IO) {
            saveUserToLocal(user)
            userService.updateUserAsync(id, user).await()
        }

}