package com.example.mymovie.ui.screens.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovie.data.local.repository.MovieRepository
import com.example.mymovie.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class FavouritesViewModel @Inject constructor(
    repository: MovieRepository
): ViewModel() {

    val readFavouritesMovie: LiveData<List<Result>> = repository.readAllMovie
}