package com.example.mymovie.data.remote.model

data class movieModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)