package com.github.vaibhav.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.github.vaibhav.R
import com.github.vaibhav.data.UserPreferences
import com.github.vaibhav.ui.home.HomeActivity
import com.github.vaibhav.ui.startNewActivity

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            Toast.makeText(this, it ?: "token is null", Toast.LENGTH_SHORT).show()
            if (it != null)
                startNewActivity(HomeActivity::class.java)
        })
    }
}