package com.example.mymovie.ui.auntification.screens.registr

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.data.remote.firebase.AuthenticationRepositoryImpl
import com.example.mymovie.data.remote.firebase.Resours
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel(){

    private val _state = MutableLiveData<Resours<FirebaseUser>?>(null)
    val state: MutableLiveData<Resours<FirebaseUser>?> = _state

    fun registration(email: String, password: String) = viewModelScope.launch {
        _state.value = Resours.Loading
        val result = repository.createUser(email, password)
        _state.value = result
    }

    private fun inputCheck (title: String, subTitle: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(subTitle))
    }
}