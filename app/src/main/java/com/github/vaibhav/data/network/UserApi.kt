package com.github.vaibhav.data.network

import com.github.vaibhav.data.responses.HomeDataResponse
import com.github.vaibhav.data.responses.UserListResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("users/2")
    suspend fun getUsers(): HomeDataResponse


    @POST("logout")
    suspend fun logout(): ResponseBody

    @GET("users?page=2")
    suspend fun getUserList() : UserListResponse



}