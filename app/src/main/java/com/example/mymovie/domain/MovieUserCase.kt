package com.example.mymovie.domain

import com.example.mymovie.domain.userCase.*

data class MovieUserCase(
    val getPopularMovie: GetPopularMovieCase,
    val getTopRatedMovieCase: GetTopRatedMovieCase,
    val getUpcomingMovieCase: GetUpcomingMovieCase,
    val getNowPlayingCase: GetNowPlayingCase,
    val getRecommendationsCase: GetRecommendationsCase,
    val getSimilarMovieCase: GetSimilarMovieCase,
    val searchMovieCase: SearchMovieCase,
    val readAllLocalMovieCase: ReadAllLocalMovieCase,
    val insertLocalMovieCase: InsertLocalMovieCase,
    val deleteLocalMovieCase: DeleteLocalMovieCase,
    val getLocalSizeTableCase: GetLocalSizeTableCase,
    val deleteLocalTableCase: DeleteLocalTableCase,
)
