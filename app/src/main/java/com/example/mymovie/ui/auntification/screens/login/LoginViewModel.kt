package com.example.mymovie.ui.auntification.screens.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.data.remote.firebase.Resours
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel(){

    private val _state = MutableLiveData<Resours<FirebaseUser>?>(null)
    val state: MutableLiveData<Resours<FirebaseUser>?> = _state

    fun singIn(email: String, password: String) = viewModelScope.launch {
        _state.value = Resours.Loading
        val result = repository.singUser(email, password)
        _state.value = result
    }
}