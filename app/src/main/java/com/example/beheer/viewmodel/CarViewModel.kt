package com.example.beheer.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import data.local.Dao.BeheerDatabase
import data.model.Car
import data.model.CarWrapper
import data.remote.ServiceBuilder
import data.remote.WebService.CarService
import kotlinx.coroutines.launch
import repository.CarRepository
import retrofit2.Response

class CarViewModel(application: Application) : AndroidViewModel(application) {
    private val carRepository: CarRepository

    init {
        val carService = ServiceBuilder.buildService(CarService::class.java)
        val carDao = BeheerDatabase.getDatabase(application).carDao()
        carRepository = CarRepository(carService, carDao)
    }



    private val _getResponse = MutableLiveData<Response<Car>>()
    val getResponse: LiveData<Response<Car>>
        get() = _getResponse

    private val _getResponses = MutableLiveData<Response<CarWrapper>>()
    val getResponses: LiveData<Response<CarWrapper>>
        get() = _getResponses

    private val _updateResponse = MutableLiveData<Response<Car>>()
    val updateResponse: LiveData<Response<Car>>
        get() = _updateResponse

    private val _insertResponse = MutableLiveData<Response<Void>>()
    val insertResponse: LiveData<Response<Void>>
        get() = _insertResponse


    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse: MutableLiveData<Response<Void>>
        get() = _deleteResponse

    private val _getLocalResponse = MutableLiveData<List<Car>>()
    val getLocalResponse: MutableLiveData<List<Car>>
        get() = _getLocalResponse


    fun getCars() = viewModelScope.launch {
        _getResponses.postValue(carRepository.getCars())
    }

    fun getCarById(id: Long) = viewModelScope.launch {
        _getResponse.postValue(carRepository.getCarById(id))
    }

    fun getCarsByUserId(userId: Long) = viewModelScope.launch {
        _getResponses.postValue(carRepository.getCarsByUserId(userId))
    }

    fun insertCar(car: Car) = viewModelScope.launch {
        _insertResponse.postValue(carRepository.insertCar(car))
    }

    fun updateCar(id: Long, car: Car) = viewModelScope.launch {
        _updateResponse.postValue(carRepository.updateCaProfile(id, car))
    }

    fun deleteCar(id: Long) = viewModelScope.launch {
        _deleteResponse.postValue(carRepository.deleteCar(id))
    }

    fun getCarsFromLocal() =
        viewModelScope.launch {
            _getLocalResponse.postValue(carRepository.getCarsFromLocal().value)
        }



}