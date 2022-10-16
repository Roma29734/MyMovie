package com.example.mymovie.ui.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.data.local.MovieDataBase
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.remote.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var movieLocalRepository: MovieRepository

    init {
        val movieDao = MovieDataBase.getDataBase(application).movieDao()
        movieLocalRepository = MovieRepository(movieDao)
    }


    fun addFavourites(moveiModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieLocalRepository.insertMovie(moveiModel)
        }
    }

    fun deleteFavourites(moveiModel: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            movieLocalRepository.deleteMovie(moveiModel)
        }
    }
}