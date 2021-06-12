package com.github.vaibhav.ui.base

import androidx.lifecycle.ViewModel
import com.github.vaibhav.data.network.UserApi
import com.github.vaibhav.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {


    suspend fun logout(api:UserApi) = withContext(Dispatchers.IO){

        repository.logout(api)
    }
}