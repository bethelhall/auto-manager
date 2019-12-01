package repository

import android.util.Log
import androidx.lifecycle.LiveData
import data.local.Dao.CarDao
import data.model.Car
import data.model.CarWrapper
import data.remote.WebService.CarService

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CarRepository(private val carService: CarService, private val carDao: CarDao) {

    private fun saveCarsToLocal(cars: List<Car>) {
        for (car in cars) {
            carDao.insertCar(car)
        }
    }

    private fun saveCarsToLocal(car: Car) {
        carDao.insertCar(car)
    }

    suspend fun getCarsFromLocal(): LiveData<List<Car>> =
        withContext(Dispatchers.IO) {
            carDao.getCars()
        }

    private fun deleteCarFromLocal(carId: Long) {
        carDao.deleteCarById(carId)
    }

    suspend fun getCars(): Response<CarWrapper> {
        lateinit var cars: Response<CarWrapper>
        withContext(Dispatchers.IO) {
            val allCars = carService.getItemsAsync().await()
            saveCarsToLocal(allCars.body()!!.embeddedItems.allCar)  //saving item to a local database
            withContext(Dispatchers.Main) {
                cars = allCars
            }
        }
        return cars
    }

    suspend fun getCarById(id: Long): Response<Car> =
        withContext(Dispatchers.IO) {
            carService.getCarAsync(id).await()
        }

    suspend fun getCarsByUserId(id: Long): Response<CarWrapper> =
        withContext(Dispatchers.IO) {
            carService.getCarsByUserIdAsync(id).await()
        }

    suspend fun insertCar(car: Car): Response<Void> =
        withContext(Dispatchers.IO) {
            saveCarsToLocal(car)   //updating the local database
            carService.insertCarAsync(car).await()
        }

    suspend fun updateCaProfile(id: Long, car: Car): Response<Car> =
        withContext(Dispatchers.IO) {
            saveCarsToLocal(car)    //updating the local database
            carService.updateCarAsync(id, car).await()
        }

    suspend fun deleteCar(id: Long): Response<Void> =
        withContext(Dispatchers.IO) {
            deleteCarFromLocal(id)
            carService.deleteCarAsync(id).await()
        }


}