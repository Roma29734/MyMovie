package com.example.mymovie.data.remote.retrofit.repository

import com.example.mymovie.data.remote.retrofit.api.RetrofitInstance
import com.example.mymovie.data.model.movieModel
import retrofit2.Response

class RemoteRepository {
    suspend fun getMuvies(): Response<movieModel> {
        return RetrofitInstance.api.getPopularMovie()
    }
}