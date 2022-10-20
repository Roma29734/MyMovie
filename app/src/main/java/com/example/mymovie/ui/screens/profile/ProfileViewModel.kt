package com.example.mymovie.ui.screens.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mymovie.data.remote.firebase.AuthenticationRepositoryImpl

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val authenticationRepositoryImpl = AuthenticationRepositoryImpl()

    private val context = application

    suspend fun exit() {
        authenticationRepositoryImpl.logOut()
    }

}