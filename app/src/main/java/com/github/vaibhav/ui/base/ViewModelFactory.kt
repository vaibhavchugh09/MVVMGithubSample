package com.github.vaibhav.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.vaibhav.data.repository.AuthRepository
import com.github.vaibhav.data.repository.BaseRepository
import com.github.vaibhav.data.repository.UserListRepository
import com.github.vaibhav.data.repository.UserRepository
import com.github.vaibhav.ui.auth.AuthViewModel
import com.github.vaibhav.ui.home.HomeViewModel
import com.github.vaibhav.ui.userList.UserListViewModel

class ViewModelFactory(
    private val repository: BaseRepository

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(UserListViewModel::class.java) -> UserListViewModel(repository as UserListRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
