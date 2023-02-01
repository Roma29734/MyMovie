package com.example.mymovie.ui.screens.detail

import androidx.paging.PagingData
import com.example.mymovie.data.model.Result
import com.example.mymovie.utils.LoadState

data class DetailState(
    var loadState: LoadState = LoadState.SUCCESS,
    var successRec: PagingData<Result>? = null,
    var successSimilar: PagingData<Result>? = null,
)