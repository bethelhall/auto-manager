package data.remote.WebService
import data.model.User
import data.model.UserWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface UserService {

    @GET("users")
    fun getUsersAsync(): Deferred<Response<UserWrapper>>

    @GET("users/{id}")
    fun getUserAsync(@Path("id") id: Long): Deferred<Response<User>>

    @POST("users")
    fun insertUserAsync(@Body newUser: User): Deferred<Response<Void>>

    @PUT("users/{id}")
    fun updateUserAsync(@Path("id") id: Long, @Body user: User): Deferred<Response<User>>

    @DELETE("users/id")
    fun deleteUserAsync(@Path("id") id: Long): Deferred<Response<Void>>
}