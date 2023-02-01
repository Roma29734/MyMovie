package com.example.mymovie.data.remote.retrofit.repository

import com.example.mymovie.data.remote.retrofit.api.RetrofitInstance
import com.example.mymovie.data.model.movieModel
import com.example.mymovie.utils.API_KEY
import retrofit2.Response
import retrofit2.http.Query

class RemoteRepository {
    suspend fun getPopularMovie(page: Int): movieModel {
        return RetrofitInstance.api.getPopularMovie(
            key = API_KEY,
            language = "ru-RUS",
            page = page
        )
    }

    suspend fun getTopRatedMovie(page: Int): movieModel {
        return RetrofitInstance.api.getTopRatedMovie(
            key = API_KEY,
            language = "ru-RUS",
            page = page
        )
    }

    suspend fun getUpcomingMovie(page: Int): movieModel {
        return RetrofitInstance.api.getUpcomingMovie(
            key = API_KEY,
            language = "ru-RUS",
            page = page
        )
    }

    suspend fun getNowPlaying(page: Int): movieModel {
        return RetrofitInstance.api.getNowPlaying(
            key = API_KEY,
            language = "ru-RUS",
            page = page,
            region = "RU"
        )
    }

    suspend fun searchMovie(query: String, page: Int): movieModel {
        return RetrofitInstance.api.searchMovie(
            key = API_KEY,
            language = "ru-RUS",
            query = query,
            page = page,
            adalt = false
        )
    }

    suspend fun getRecommendations(id: Int, page: Int): movieModel {
        return RetrofitInstance.api.getRecommendations(
            id = id,
            key = API_KEY,
            language = "ru-RUS",
            page = page
        )
    }

    suspend fun getSimilarMovie(id: Int, page: Int): movieModel {
        return RetrofitInstance.api.getSimilarMovie(
            id = id,
            key = API_KEY,
            language = "ru-RUS",
            page = page
        )
    }
}