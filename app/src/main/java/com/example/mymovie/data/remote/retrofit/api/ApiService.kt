package com.example.mymovie.data.remote.retrofit.api

import com.example.mymovie.data.model.movieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    https://api.themoviedb.org/3/movie/popular?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-RUS&page=1
//    https://api.themoviedb.org/3/movie/top_rated?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-Rus&page=1
//    https://api.themoviedb.org/3/movie/upcoming?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-Rus&page=1
//    https://api.themoviedb.org/3/search/movie?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-Rus&query=a&page=1&include_adult=false

    @GET("3/movie/popular?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-RUS&page=1")
    suspend fun getPopularMovie(): Response<movieModel>

    @GET("3/movie/top_rated?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-Rus&page=1")
    suspend fun getTopRatedMovie(): Response<movieModel>

    @GET("3/movie/upcoming?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-Rus&page=1")
    suspend fun getUpcomingMovie(): Response<movieModel>

    @GET("3/search/movie?api_key=45ffb7fda0e37319afe521f67ba1b0f8&language=ru-Rus")
    suspend fun searchMovie(
        @Query("query")query: String,
        @Query("page")page: Int,
        @Query("include_adult")adalt: Boolean
    ): Response<movieModel>
}