package com.example.mymovie.ui.auntification.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.ui.auntification.service.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private var aunthenticationRepository = AuthenticationRepository()

    private var context = application

    fun singUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            aunthenticationRepository.singuser(email, password, context)
        }
    }
}