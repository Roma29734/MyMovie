package com.example.mymovie.data.remote.repository

import com.example.mymovie.data.remote.api.RetrofitInstance
import com.example.mymovie.data.remote.model.movieModel
import retrofit2.Response

class RemoteRepository {
    suspend fun getMuvies(): Response<movieModel> {
        return RetrofitInstance.api.getPopularMovie()
    }
}