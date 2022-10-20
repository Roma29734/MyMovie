package com.example.mymovie.ui.auntification.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.remote.firebase.AuthenticationRepositoryImpl
import com.example.mymovie.data.remote.firebase.Resours
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private var aunthenticationRepository = AuthenticationRepositoryImpl()

    private var context = application

    private val _state = MutableLiveData<Resours<FirebaseUser>?>(null)
    val state: MutableLiveData<Resours<FirebaseUser>?> = _state


    fun singIn(email: String, password: String) = viewModelScope.launch {
        _state.value = Resours.Loading
        val result = aunthenticationRepository.singUser(email, password)
        _state.value = result
    }
}