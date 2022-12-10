package com.example.mymovie.ui.screens.search

import com.example.mymovie.data.model.ParentModel
import com.example.mymovie.data.model.Result
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.utils.LoadState

data class SearchState (
    var loadState: LoadState = LoadState.SUCCESS,
    var successState: movieModel? = null,
)