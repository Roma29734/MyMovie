package com.example.mymovie.data.local.repository

import androidx.lifecycle.LiveData
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.model.Result

interface MovieRepository {
    val readAllMovie: LiveData<List<Result>>

    suspend fun insertMovie(movieItemModel: Result)

    suspend fun deleteMovie(movieItemModel: Result)
}