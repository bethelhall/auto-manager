package data.local.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import data.model.Car

@Dao
interface CarDao {

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCarById(id: Long): LiveData<Car>


    @Query("SELECT * FROM cars")
    fun getCars(): LiveData<List<Car>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(car: Car)

    @Query("DELETE FROM cars WHERE id = :id")
    fun deleteCarById(id: Long)


}
