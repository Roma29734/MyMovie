package com.example.mymovie.ui.screens.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.data.remote.firebase.AuthenticationRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel() {

//    private val authenticationRepositoryImpl = AuthenticationRepositoryImpl()

    suspend fun exit() {
        repository.logOut()
    }

}