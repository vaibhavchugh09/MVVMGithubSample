package com.github.vaibhav.data.repository

import com.github.vaibhav.data.network.UserApi

class UserListRepository(
    private val api: UserApi,
) : BaseRepository() {


    suspend fun getUserList() = safeApiCall {

        api.getUserList()
    }



}