package com.example.mymovie.ui.screens.home

import com.example.mymovie.data.model.ParentModel
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.utils.LoadState

data class HomeState(
    var loadState: LoadState = LoadState.SUCCESS,
    var successTopRated: ParentModel? = null,
    var successPopular: ParentModel? = null,
    var successUpcoming: ParentModel? = null,
    var successNow: ParentModel? = null,
)