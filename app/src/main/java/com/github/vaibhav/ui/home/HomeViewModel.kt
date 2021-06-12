package com.github.vaibhav.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.vaibhav.data.network.Resource
import com.github.vaibhav.data.repository.UserRepository
import com.github.vaibhav.data.responses.HomeDataResponse
import com.github.vaibhav.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel
    (

    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<HomeDataResponse>> = MutableLiveData()


    val user: LiveData<Resource<HomeDataResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {

        _user.value = repository.getUser()
    }


}