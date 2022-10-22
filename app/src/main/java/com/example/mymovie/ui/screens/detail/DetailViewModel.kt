package com.example.mymovie.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
): ViewModel() {

    fun addFavourites(moveiModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.insertMovie(moveiModel)
        }
    }

    fun deleteFavourites(moveiModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.deleteMovie(moveiModel)
        }
    }
}