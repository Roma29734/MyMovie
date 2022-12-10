package com.example.mymovie.ui.screens.detail

import com.example.mymovie.data.model.movieModel
import com.example.mymovie.utils.LoadState

data class DetailState(
    var loadState: LoadState = LoadState.SUCCESS,
    var successRec: movieModel? = null,
    var successSimilar: movieModel? = null,
)