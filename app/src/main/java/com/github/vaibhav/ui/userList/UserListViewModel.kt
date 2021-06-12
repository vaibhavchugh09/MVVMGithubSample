package com.github.vaibhav.ui.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.vaibhav.data.network.Resource
import com.github.vaibhav.data.repository.UserListRepository
import com.github.vaibhav.data.responses.UserListResponse
import com.github.vaibhav.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: UserListRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<UserListResponse>> = MutableLiveData()

    val user: LiveData<Resource<UserListResponse>>
        get() = _user

    fun getUserList() = viewModelScope.launch {

        _user.value = repository.getUserList()
    }


}