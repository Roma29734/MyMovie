package com.example.mymovie.data.remote.api

import com.example.mymovie.data.remote.model.movieModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

//    https://api.themoviedb.org/3/movie/popular?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-RUS&page=1

    @GET("3/movie/popular?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-RUS&page=1")
    suspend fun getPopularMovie(): Response<movieModel>
}