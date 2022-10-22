package com.example.mymovie.data.local.repository

import androidx.lifecycle.LiveData
import com.example.mymovie.data.local.dao.MovieDao
import com.example.mymovie.data.model.Result
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
): MovieRepository {

    override val readAllMovie: LiveData<List<Result>>
        get() = movieDao.getAllMovie()

    override suspend fun insertMovie(movieItemModel: Result) {
        movieDao.insertMovie(movieItemModel)
    }

    override suspend fun deleteMovie(movieItemModel: Result) {
        movieDao.deleteMovie(movieItemModel)
    }
}