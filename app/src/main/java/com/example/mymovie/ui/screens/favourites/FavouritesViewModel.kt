package com.example.mymovie.ui.screens.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result
import com.example.mymovie.domain.MovieUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val movieUserCase: MovieUserCase,
) : ViewModel() {

    val readFavouritesMovie: LiveData<List<Result>> = movieUserCase.readAllLocalMovieCase()
}