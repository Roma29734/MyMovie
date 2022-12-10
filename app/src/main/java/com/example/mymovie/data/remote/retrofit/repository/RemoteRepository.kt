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

    suspend fun getNowPlaying(): movieModel {
        return RetrofitInstance.api.getNowPlaying(key = API_KEY, language = "ru-RUS", page = 1, region = "RU")
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

    suspend fun getRecommendations(id: Int): movieModel {
        return RetrofitInstance.api.getRecommendations(id = id, key = API_KEY, language = "ru-RUS", page = 1)
    }

    suspend fun getSimilarMovie(id: Int): movieModel {
        return RetrofitInstance.api.getSimilarMovie(id = id, key = API_KEY, language = "ru-RUS", page = 1)
    }
}