package com.example.mymovie.ui.screens.home

import com.example.mymovie.data.model.movieModel
import com.example.mymovie.utils.LoadState

data class HomeState(
    var loadState: LoadState = LoadState.SUCCESS,
    var successState: movieModel? = null
)