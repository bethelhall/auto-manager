package data.remote.WebService
import data.model.Car
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface  ItemService {

    @GET("items")
    fun getItemsAsync(): Deferred<Response<ItemsWrapper>>

    @GET("items/{id}")
    fun getItemAsync(@Path("id") id: Long): Deferred<Response<Car>>

    @GET("users/{id}/items")
    fun getItemsByUserIdAsync(@Path("id") id: Long): Deferred<Response<ItemsWrapper>>

    @POST("items")
    fun insertItemAsync(@Body newCar: Car): Deferred<Response<Void>>

    @PUT("items/{id}")
    fun updateItemAsync(@Path("id") id: Long, @Body car: Car): Deferred<Response<Car>>

    @DELETE("items/{id}")
    fun deleteItemAsync(@Path("id") id: Long): Deferred<Response<Void>>
}