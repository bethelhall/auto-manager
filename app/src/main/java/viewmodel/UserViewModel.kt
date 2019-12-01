package com.example.chareta.viewmodel

import android.app.Application
import android.app.*;
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.beheer.R
import data.local.Dao.BeheerDatabase
import data.model.User
import data.remote.ServiceBuilder
import data.remote.WebService.UserService
import kotlinx.coroutines.launch
import repository.UserRepository
import retrofit2.Response
import view.LoginFragment
import view.RegistrationFragment

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository

    init {
        val userService = ServiceBuilder.buildService(UserService::class.java)
        val userDao = BeheerDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userService, userDao)
    }

    private val _getResponse = MutableLiveData<Response<User>>()
    val getResponse: LiveData<Response<User>>
        get() = _getResponse

    private val _getResponses = MutableLiveData<Response<UsersWrapper>>()
    val getResponses: LiveData<Response<UsersWrapper>>
        get() = _getResponses

    private val _updateResponse = MutableLiveData<Response<User>>()
    val updateResponse: LiveData<Response<User>>
        get() = _updateResponse

    private val _insertResponse = MutableLiveData<Response<Void>>()
    val insertResponse: LiveData<Response<Void>>
        get() = _insertResponse

    private val _deleteResponse = MutableLiveData<Response<Void>>()
    val deleteResponse: MutableLiveData<Response<Void>>
        get() = _deleteResponse


    fun getUsers() = viewModelScope.launch {
        _getResponses.postValue(userRepository.getUsers())
    }

    fun getUserById(id: Long) = viewModelScope.launch {
        _getResponse.postValue(userRepository.getUserById(id))
    }

    fun insertUser(user: User) = viewModelScope.launch {
        _insertResponse.postValue(userRepository.insertUser(user))
    }

    fun updateUser(id: Long, user: User) = viewModelScope.launch {
        _updateResponse.postValue(userRepository.updateUser(id, user))
    }

    fun deleteUser(id: Long) = viewModelScope.launch {
        _deleteResponse.postValue(userRepository.deleteUser(id))
    }


    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()
    val userUsername = MutableLiveData<String>()

    val userPassword = MutableLiveData<String>()


    val userPhoneno = MutableLiveData<String>()
    val userAddress = MutableLiveData<String>()

    val userConfirmPassword = MutableLiveData<String>()

    val registerConfirmation = MutableLiveData<String>()

    fun cancelButtonClicked() {

    }

    fun loginButtonClicked() {
        val postedFragment = LoginFragment()
        postedFragment.requireFragmentManager()
            .beginTransaction()
            .replace(R.id.container, postedFragment)
            .commit()
    }

    fun notRegisteredClicked() {
        val registerFragment = RegistrationFragment()
        registerFragment.requireFragmentManager()
            .beginTransaction()
            .replace(R.id.container, registerFragment)
            .commit()
    }

    fun onRegisterButtonClicked() {

    }

    fun onBackButtonClicked() {

    }


}