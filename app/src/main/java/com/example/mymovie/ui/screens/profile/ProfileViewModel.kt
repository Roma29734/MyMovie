package com.example.mymovie.ui.screens.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.data.remote.firebase.AuthenticationRepositoryImpl
import com.example.mymovie.domain.MovieUserCase
import com.example.mymovie.utils.SaveShared
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
    private val movieUserCase: MovieUserCase,
) : ViewModel() {

    val user = repository.currentUser
    val favMovie: MutableLiveData<Int> = MutableLiveData()

    fun getFavMovie() {
        viewModelScope.launch {
           favMovie.value = movieUserCase.getLocalSizeTableCase()
        }
    }

    fun deleteBase() {
        viewModelScope.launch {
            movieUserCase.deleteLocalTableCase()
        }
    }

    suspend fun exit(context: Context) {
        repository.logOut()
        SaveShared.deleteAll(context)
    }
}