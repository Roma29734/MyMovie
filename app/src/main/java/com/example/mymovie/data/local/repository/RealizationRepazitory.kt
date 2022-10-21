package com.example.mymovie.data.local.repository

import com.example.mymovie.data.local.dao.MovieDao

//class RealizationRepazitory(private val movieDao: MovieDao): MovieRepository {
//    override val readlAllMovie: LiveData<List<Result>>
//        get() = movieDao.getAllMovie()
//
//    override suspend fun insertMovie(movieItemModel: Result, onSuccess: () -> Unit) {
//        movieDao.insertMovie(movieItemModel)
//        onSuccess()
//    }
//
//    override suspend fun deleteMovie(movieItemModel: Result, onSuccess: () -> Unit) {
//        movieDao.deleteMovie(movieItemModel)
//        onSuccess()
//    }
//}