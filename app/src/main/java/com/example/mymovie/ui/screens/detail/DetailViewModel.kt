package com.example.mymovie.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result
import com.example.mymovie.data.remote.firebase.AuthenticationRepository
import com.example.mymovie.domain.MovieUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
    private val authenticationRepository: AuthenticationRepository,
): ViewModel() {

    val user = authenticationRepository.currentUser

    fun addFavourites(movieModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.insertLocalMovieCase(movieModel)
        }
    }

    fun deleteFavourites(movieModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUserCase.deleteLocalMovieCase(movieModel)
        }
    }
}