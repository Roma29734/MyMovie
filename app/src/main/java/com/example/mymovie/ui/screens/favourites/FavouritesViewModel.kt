package com.example.mymovie.ui.screens.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mymovie.data.local.MovieDataBase
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result

class FavouritesViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var movieLocalRepository: MovieRepository
    val readFavouritesMovei: LiveData<List<Result>>

    init {
        val movieDao = MovieDataBase.getDataBase(application).movieDao()
        movieLocalRepository = MovieRepository(movieDao)
        readFavouritesMovei = movieLocalRepository.readlAllMovie
    }
}