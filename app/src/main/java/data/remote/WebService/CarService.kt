package data.remote.WebService
import data.model.Car
import data.model.CarWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface  CarService {

    @GET("cars")
    fun getItemsAsync(): Deferred<Response<CarWrapper>>

    @GET("cars/{id}")
    fun getCarAsync(@Path("id") id: Long): Deferred<Response<Car>>

    @GET("users/{id}/cars")
    fun getCarsByUserIdAsync(@Path("id") id: Long): Deferred<Response<CarWrapper>>

    @POST("car")
    fun insertCarAsync(@Body newCar: Car): Deferred<Response<Void>>

    @PUT("cars/{id}")
    fun updateCarAsync(@Path("id") id: Long, @Body car: Car): Deferred<Response<Car>>

    @DELETE("cars/{id}")
    fun deleteCarAsync(@Path("id") id: Long): Deferred<Response<Void>>
}