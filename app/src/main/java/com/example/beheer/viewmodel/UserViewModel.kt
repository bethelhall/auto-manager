package com.example.beheer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.beheer.R
import data.local.Dao.BeheerDatabase
import data.model.User
import data.model.UserWrapper
import data.remote.ServiceBuilder
import data.remote.WebService.UserService
import kotlinx.coroutines.launch
import repository.UserRepository
import retrofit2.Response
import view.LoginFragment
import view.RegisterFragment



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

    private val _getResponses = MutableLiveData<Response<UserWrapper>>()
    val getResponses: LiveData<Response<UserWrapper>>
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
        val registerFragment = RegisterFragment()
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
