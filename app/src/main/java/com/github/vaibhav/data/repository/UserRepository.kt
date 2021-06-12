package com.github.vaibhav.data.repository

import com.github.vaibhav.data.network.UserApi

class UserRepository(
    private val api: UserApi,
) : BaseRepository() {


    suspend fun getUser() = safeApiCall {

        api.getUsers()
    }



}