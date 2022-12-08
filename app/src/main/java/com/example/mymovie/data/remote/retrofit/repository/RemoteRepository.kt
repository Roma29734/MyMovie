package com.example.mymovie.data.remote.retrofit.repository

import com.example.mymovie.data.remote.retrofit.api.RetrofitInstance
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.utils.API_KEY
import retrofit2.Response
import retrofit2.http.Query

class RemoteRepository {
    suspend fun getPopularMovie(): movieModel {
        return RetrofitInstance.api.getPopularMovie(key = API_KEY, language = "ru-RUS", page = 1)
    }

    suspend fun getTopRatedMovie(): movieModel {
        return RetrofitInstance.api.getTopRatedMovie(key = API_KEY, language = "ru-RUS", page = 1)
    }

    suspend fun getUpcomingMovie(): movieModel {
        return RetrofitInstance.api.getUpcomingMovie(key = API_KEY, language = "ru-RUS", page = 1)
    }

    suspend fun searchMovie(query: String): movieModel {
        return RetrofitInstance.api.searchMovie(
            key = API_KEY,
            language = "ru-RUS",
            query = query,
            page = 1,
            adalt = false
        )
    }
}