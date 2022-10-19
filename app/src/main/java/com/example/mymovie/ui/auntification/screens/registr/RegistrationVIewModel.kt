package com.example.mymovie.ui.auntification.screens.registr

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.ui.auntification.service.AuthenticationRepository
import kotlinx.coroutines.launch

class RegistrationVIewModel(application: Application): AndroidViewModel(application) {

    private var aunthenticationRepository = AuthenticationRepository()

    private var context = application
    fun registrNewUser(email: String, password: String) {
        viewModelScope.launch {
            aunthenticationRepository.createUser(email, password, context)
        }
    }
}