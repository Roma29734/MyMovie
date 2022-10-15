package com.example.mymovie.data.local.repository

import androidx.lifecycle.LiveData
import com.example.mymovie.data.remote.model.Result

interface MovieRepository {
    val readlAllMovie: LiveData<List<Result>>

    suspend fun insertMovie(movieItemModel: Result, onSuccess:() -> Unit)

    suspend fun deleteMovie(movieItemModel: Result, onSuccess:() -> Unit)
}