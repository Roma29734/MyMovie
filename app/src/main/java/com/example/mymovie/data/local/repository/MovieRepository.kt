package com.example.mymovie.data.local.repository

import androidx.lifecycle.LiveData
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.model.Result

class MovieRepository(private val movieDao: MovieDao) {
    val readlAllMovie: LiveData<List<Result>> = movieDao.getAllMovie()

    suspend fun insertMovie(movieItemModel: Result) {
        movieDao.insertMovie(movieItemModel)
    }

    suspend fun deleteMovie(movieItemModel: Result) {
        movieDao.deleteMovie(movieItemModel)
    }
}