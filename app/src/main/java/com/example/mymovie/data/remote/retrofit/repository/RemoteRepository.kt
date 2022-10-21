package com.example.mymovie.data.remote.retrofit.repository

import com.example.mymovie.data.remote.retrofit.api.RetrofitInstance
import com.example.mymovie.data.model.movieModel
import retrofit2.Response

class RemoteRepository {
    suspend fun getPopularMovie(): Response<movieModel> {
        return RetrofitInstance.api.getPopularMovie()
    }

    suspend fun getTopRatedMovie(): Response<movieModel> {
        return RetrofitInstance.api.getTopRatedMovie()
    }

    suspend fun getUpcomingMovie(): Response<movieModel> {
        return RetrofitInstance.api.getUpcomingMovie()
    }
}