package com.github.vaibhav.data.repository

import com.github.vaibhav.data.UserPreferences
import com.github.vaibhav.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences

) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String

    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String) {

        preferences.saveAuthToken((token))
    }

}